/*
 * Created by joenggyu0@gmail.com on 4/7/20
 * Github : http://github.com/lenkim
 */

package net.slipp.franchise.domain.model.recruit.inqueryitem;

import net.slipp.common.domain.model.DomainEventPublisher;
import net.slipp.franchise.domain.model.meetup.MeetupId;
import net.slipp.franchise.domain.model.recruit.Recruit;
import net.slipp.franchise.domain.model.recruit.RecruitCreatedEvent;
import net.slipp.franchise.domain.model.recruit.RecruitId;
import net.slipp.franchise.domain.model.recruit.RecruitIdGenerator;

import javax.validation.Validation;
import javax.validation.Validator;
import javax.validation.ValidatorFactory;
import java.util.Objects;

import static net.slipp.franchise.domain.model.recruit.Status.BEGIN;

public class InquiryFactory {

    private final InquiryGenerator inquiryGenerator;

    public InquiryFactory(InquiryGenerator inquiryGenerator) {
        Objects.nonNull(inquiryGenerator);
        this.inquiryGenerator = inquiryGenerator;
    }

    public InquiryItem create(String question) {

        if (Objects.isNull(question)){
            throw new IllegalArgumentException();
        }

        InquiryItemId id = inquiryGenerator.gen();
        InquiryItem inquiryItem = new InquiryItem(id, question);

        ValidatorFactory validatorFactory = Validation.buildDefaultValidatorFactory();
        Validator validator = validatorFactory.getValidator();
        validator.validate(inquiryItem);

        return inquiryItem;
    }
}
