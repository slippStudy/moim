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
    private Status status;

    Recruitment(@NotNull RecruitmentId id, @NotNull MeetupId meetupId, EndpolicyId endpolicyId) {
        this.id = id;
        this.meetupId = meetupId;
        this.endpolicyId = endpolicyId;
        this.status = Status.BEGIN;
    }

    public MeetupId getMeetupId() {
        return meetupId;
    }

    public RecruitmentId getId() {
        return id;
    }

    public Status getStatus() {
        return status;
    }

    public void setStatus(Status status) {
        this.status = status;
    }

    public void start() {
        if (this.status == Status.START){
            throw new IllegalStateException();
        }
        this.status = Status.START;
    }

    public void finish() {
        if (this.status == Status.FINISH){

        }
        this.status = Status.FINISH;
    }
}
