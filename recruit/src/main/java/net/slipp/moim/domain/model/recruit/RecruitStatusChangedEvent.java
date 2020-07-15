/**
 * Created by joenggyu0@gmail.com on 4/28/20
 * Github : http://github.com/lenkim
 */

package net.slipp.moim.domain.model.recruit;

import net.slipp.ddd.domain.DomainEvent;
import net.slipp.utils.Assertions;

import java.time.LocalDateTime;

public class RecruitStatusChangedEvent implements DomainEvent {

    private final RecruitId recruitId;
    private final Status status;

    public RecruitStatusChangedEvent(RecruitId recruitId, Status status) {
        Assertions.assertArgumentNotNull(recruitId, "recruitId는 존재해야 합니다.");
        Assertions.assertArgumentNotNull(status, "status는 존재해야 합니다.");

        this.recruitId = recruitId;
        this.status = status;
    }

    @Override
    public LocalDateTime occurredOn() {
        return null;
    }

    public RecruitId getRecruitId() {
        return this.recruitId;
    }

    public Status getStatus() {
        return this.status;
    }
}
