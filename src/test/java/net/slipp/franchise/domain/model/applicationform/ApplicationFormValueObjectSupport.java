package net.slipp.franchise.domain.model.applicationform;

import net.slipp.franchise.domain.model.meetup.MeetupId;
import net.slipp.franchise.domain.model.recruit.RecruitId;

public interface ApplicationFormValueObjectSupport {

    RecruitId recruitId = RecruitId.of("1");
    MeetupId meetupId = MeetupId.of("1");
}
