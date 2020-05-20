/**
 * Created by joenggyu0@gmail.com on 4/7/20
 * Github : http://github.com/lenkim
 */

package net.slipp.franchise.domain.model.recruit;

import com.google.common.collect.Sets;
import net.slipp.common.Assertions;
import net.slipp.common.domain.model.DomainEventPublisher;

import javax.validation.ConstraintViolation;
import javax.validation.Validation;
import javax.validation.Validator;
import javax.validation.ValidatorFactory;
import javax.validation.constraints.NotNull;
import java.util.Collections;
import java.util.HashSet;
import java.util.Set;
import java.util.stream.Collectors;

import static net.slipp.franchise.domain.model.recruit.Status.*;

public class Recruit {

    @NotNull
    private final RecruitId id;

    @NotNull
    private Title title;

    @NotNull
    private Content content;

    @NotNull
    private Status status;

    private Set<InquiryDefinition> inquiryDefinitions;

    public static Recruit Recruit(RecruitId id){
        return new Recruit(id);
    }

    private Recruit(RecruitId id) {
        this.id = id;
        this.status = BEGIN;
        this.title = Title.UNTITLED;
        this.content = Content.NO_CONTENT;
        this.setInquiryDefinitions(Sets.newHashSet());

        ValidatorFactory validatorFactory = Validation.buildDefaultValidatorFactory();
        Validator validator = validatorFactory.getValidator();
        Set<ConstraintViolation<Recruit>> violations = validator.validate(this);
        Assertions.assertStateTrue(violations.isEmpty(), violations.stream().map(ConstraintViolation::getMessage).collect(Collectors.joining()));

        DomainEventPublisher.instance().publish(new RecruitCreatedEvent(id));
    }

    public void start() {
        if (BEGIN != status()) {
            throw new IllegalStateException();
        }
        setStatus(START);
    }

    public void finish() {
        // TODO 상태 전이 책임을 다르 곳을 옮겨 주세요
        if (START != status()) {
            throw new IllegalStateException();
        }
        setStatus(FINISH);
    }

    public RecruitId id() {
        return this.id;
    }

    public Status status() {
        return status;
    }

    public Title title() {
        return title;
    }

    public Content content() {
        return content;
    }

    public Set<InquiryDefinition> allInquiryDefinitions() {
        return Collections.unmodifiableSet(this.inquiryItems());
    }

    public void addInquiryDefinition(InquiryDefinition anInquiryDefinition) {
        Assertions.assertArgumentNotNull(anInquiryDefinition, "반드시 InquiryItem 는 존재 합니다.");
        Assertions.assertStateTrue(BEGIN == status(), "BEGIN 단계 에 서만 질의문을 추가 가능합니다.");

        inquiryItems().add(anInquiryDefinition);
    }

    private Set<InquiryDefinition> inquiryItems() {
        return inquiryDefinitions;
    }

    private void setInquiryDefinitions(HashSet<InquiryDefinition> inquiryDefinitions) {
        this.inquiryDefinitions = inquiryDefinitions;
    }

    private void setStatus(@NotNull final Status status) {
        this.status = status;
        DomainEventPublisher.instance().publish(new RecruitStatusChangedEvent(this.id, this.status));
    }
}
