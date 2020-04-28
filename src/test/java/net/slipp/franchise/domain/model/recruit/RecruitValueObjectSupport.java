package net.slipp.franchise.domain.model.recruit;

import net.slipp.franchise.domain.model.meetup.MeetupId;

public interface RecruitValueObjectSupport {

    MeetupId meetupId = MeetupId.of("1");
    RecruitId recruitId = RecruitId.of("1");
}
