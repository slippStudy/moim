/**
 * Created by joenggyu0@gmail.com on 4/7/20
 * Github : http://github.com/lenkim
 */

package net.slipp.franchise.domain.model.recruitment;

import net.slipp.franchise.domain.model.meetup.MeetupId;

import javax.validation.constraints.NotNull;
import java.util.Objects;

public class RecruitmentFactory {

    private final RecruitmentIdGenerator recruitmentIdGenerator;

    public RecruitmentFactory(RecruitmentIdGenerator recruitmentIdGenerator) {
        Objects.nonNull(recruitmentIdGenerator);
        this.recruitmentIdGenerator = recruitmentIdGenerator;
    }

    public Recruitment create(MeetupId meetupId) {
        @NotNull RecruitmentId id = recruitmentIdGenerator.gen();
        Recruitment recruitment = new Recruitment(id, meetupId, null);
        return recruitment;
    }
}
