/**
 * Created by joenggyu0@gmail.com on 4/28/20
 * Github : http://github.com/lenkim
 */

package net.slipp.franchise.domain.model.recruit;

import net.slipp.common.domain.model.DomainEvent;

import java.time.LocalDateTime;

public class RecruitStatusChangedEvent implements DomainEvent {

    @Override
    public LocalDateTime occurredOn() {
        return null;
    }
}
