/*
 * Created by joenggyu0@gmail.com on 5/11/20
 * Github : http://github.com/lenkim
 */

package net.slipp.franchise.domain.model.applicationform;

import net.slipp.common.domain.model.DomainEvent;

import java.time.LocalDateTime;

public class ApplicationFormSubmitEvent implements DomainEvent {

    @Override
    public LocalDateTime occurredOn() {
        return null;
    }
}
