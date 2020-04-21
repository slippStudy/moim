/**
 * Created by joenggyu0@gmail.com on 4/7/20
 * Github : http://github.com/lenkim
 */

package net.slipp.franchise.domain.model.recruitment;

import net.slipp.franchise.domain.model.meetup.MeetupId;

import javax.validation.Validation;
import javax.validation.Validator;
import javax.validation.ValidatorFactory;
import java.util.Objects;

import static net.slipp.franchise.domain.model.recruitment.Status.BEGIN;

public class RecruitmentFactory {

    private final RecruitmentIdGenerator recruitmentIdGenerator;

    public RecruitmentFactory(RecruitmentIdGenerator recruitmentIdGenerator) {
        Objects.nonNull(recruitmentIdGenerator);
        this.recruitmentIdGenerator = recruitmentIdGenerator;
    }

    public Recruitment create(MeetupId meetupId) {
        RecruitmentId id = recruitmentIdGenerator.gen();
        Recruitment recruitment = new Recruitment(id, meetupId, BEGIN);


        ValidatorFactory validatorFactory = Validation.buildDefaultValidatorFactory();
        Validator validator = validatorFactory.getValidator();
        validator.validate(recruitment);

        return recruitment;
    }
}
