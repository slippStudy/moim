package net.slipp.ddd.events;
/**
 * Created by joenggyu0@gmail.com on 4/28/20
 * Github : http://github.com/lenkim
 */

import java.time.LocalDateTime;

public interface DomainEvent {

    LocalDateTime occurredOn();


}
