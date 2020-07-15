package net.slipp.moim.domain.model.recruit;

import com.google.common.collect.Sets;
import net.slipp.ddd.domain.DomainEventPublisher;
import net.slipp.utils.Assertions;

import javax.validation.ConstraintViolation;
import javax.validation.Validation;
import javax.validation.Validator;
import javax.validation.ValidatorFactory;
import javax.validation.constraints.NotNull;
import java.time.LocalDateTime;
import java.util.Collections;
import java.util.Set;
import java.util.stream.Collectors;

import static net.slipp.moim.domain.model.recruit.Status.*;

public class Recruit {

    @NotNull
    private final RecruitId id;

    @NotNull
    private Title title;

    @NotNull
    private Content content;

    @NotNull
    private Status status;

    @NotNull
    private DeadLineDateTime deadLineDateTime;

    @NotNull
    private Set<InquiryDefinition> inquiryDefinitions;

    @NotNull
    private ManagedUrl managedUrl;

    public static Recruit Recruit(RecruitId id){
        return new Recruit(id);
    }

    private Recruit(RecruitId id) {
        this.id = id;
        this.status = BEGIN;
        this.title = Title.UNTITLED;
        this.content = Content.NO_CONTENT;
        this.setInquiryDefinitions(Sets.newLinkedHashSet());
        this.deadLineDateTime = DeadLineDateTime.DATETIME_NOW;
        this.managedUrl = ManagedUrl.NO_CONTENT;
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
        setStatus(WORKING);
        DomainEventPublisher.instance().publish(new RecruitStatusChangedEvent(this.id, status));
    }

    public void finish() {
        // TODO 상태 전이 책임을 다른 곳을 옮겨 주세요
        if (WORKING != status()) {
            throw new IllegalStateException();
        }
        setStatus(FINISH);
        DomainEventPublisher.instance().publish(new RecruitStatusChangedEvent(this.id, status));
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

    public DeadLineDateTime deadLineDateTime() {
        return deadLineDateTime;
    }

    public ManagedUrl managedUrl() {
        return managedUrl;
    }

    public void setTitle(Title title) {
        if (title.text().length() < 2) {
            throw new IllegalArgumentException("2글자 이상 작성해주세요.");
        }
        this.title = title;
    }

    public void setContent(Content content) {
        this.content = content;
    }

    public void setDeadLineDateTime(DeadLineDateTime deadLineDateTime) {
        DeadLineDateTime currentLocalTime = new DeadLineDateTime(LocalDateTime.now());
        if (deadLineDateTime.dateTime().isBefore(currentLocalTime.dateTime())) {
            throw new IllegalArgumentException("현재 시간보다 앞시간을 선택해주세요.");
        }
        this.deadLineDateTime = deadLineDateTime;
    }

    public Set<InquiryDefinition> allInquiryDefinitions() {
        return Collections.unmodifiableSet(this.inquiryItems());
    }

    public void addInquiryDefinition(InquiryDefinition anInquiryDefinition) {
        Assertions.assertArgumentNotNull(anInquiryDefinition, "반드시 InquiryItem 는 존재 합니다.");
        Assertions.assertStateTrue(BEGIN == status(), "BEGIN 단계 에 서만 질의문을 추가 가능합니다.");

        inquiryItems().add(anInquiryDefinition);
    }

    public void setManagedUrl(ManagedUrl managedUrl) {
        this.managedUrl = managedUrl;
    }

    public void changeInquiries(Set<InquiryDefinition> inquiryDefinitions){
        this.setInquiryDefinitions(inquiryDefinitions);
    }

    private Set<InquiryDefinition> inquiryItems() {
        return inquiryDefinitions;
    }

    private void setInquiryDefinitions(Set<InquiryDefinition> inquiryDefinitions) {
        this.inquiryDefinitions = inquiryDefinitions;
    }

    private void setStatus(@NotNull final Status status) {
        this.status = status;
        DomainEventPublisher.instance().publish(new RecruitStatusChangedEvent(this.id, this.status));
    }
}
