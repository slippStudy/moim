/**
 * Created by joenggyu0@gmail.com on 4/7/20
 * Github : http://github.com/lenkim
 */

package net.slipp.franchise.domain.model.recruit;

import net.slipp.franchise.domain.model.meetup.MeetupId;

import javax.validation.Validation;
import javax.validation.Validator;
import javax.validation.ValidatorFactory;
import java.util.Objects;

import static net.slipp.franchise.domain.model.recruit.Status.BEGIN;

public class RecruitFactory {

    private final RecruitIdGenerator recruitIdGenerator;

    public RecruitFactory(RecruitIdGenerator recruitIdGenerator) {
        Objects.nonNull(recruitIdGenerator);
        this.recruitIdGenerator = recruitIdGenerator;
    }

    public Recruit create(MeetupId meetupId) {
        RecruitId id = recruitIdGenerator.gen();
        Recruit recruit = new Recruit(id, meetupId, BEGIN);


        ValidatorFactory validatorFactory = Validation.buildDefaultValidatorFactory();
        Validator validator = validatorFactory.getValidator();
        validator.validate(recruit);

        return recruit;
    }
}
