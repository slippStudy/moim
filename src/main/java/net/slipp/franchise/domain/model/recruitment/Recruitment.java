/**
 * Created by joenggyu0@gmail.com on 4/7/20
 * Github : http://github.com/lenkim
 */

package net.slipp.franchise.domain.model.recruitment;

import net.slipp.franchise.domain.model.endpolicy.EndpolicyId;
import net.slipp.franchise.domain.model.meetup.MeetupId;

import javax.validation.constraints.NotNull;

public class Recruitment {

    @NotNull
    private final RecruitmentId id;

    @NotNull
    private final MeetupId meetupId;

    @NotNull
    private final EndpolicyId endpolicyId;

    @NotNull
    private RecruitmentStatus status;

    Recruitment(@NotNull RecruitmentId id, @NotNull MeetupId meetupId, EndpolicyId endpolicyId) {
        this.id = id;
        this.meetupId = meetupId;
        this.endpolicyId = endpolicyId;
        this.status = RecruitmentStatus.BEGIN;
    }

    public MeetupId getMeetupId() {
        return meetupId;
    }

    public RecruitmentId getId() {
        return id;
    }

    public RecruitmentStatus getStatus() {
        return status;
    }

    public void setStatus(RecruitmentStatus status) {
        this.status = status;
    }
}
