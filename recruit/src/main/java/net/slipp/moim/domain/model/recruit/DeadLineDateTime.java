/*
 * Created by joenggyu0@gmail.com on 5/22/20
 * Github : http://github.com/lenkim
 */

package net.slipp.moim.domain.model.recruit;

import java.time.LocalDateTime;

public class DeadLineDateTime {

    public static final DeadLineDateTime DATETIME_NOW = new DeadLineDateTime(LocalDateTime.now());
    private final LocalDateTime dateTime;

    public DeadLineDateTime(LocalDateTime aDateTime) {
        this.dateTime = aDateTime;
    }

    public LocalDateTime dateTime() {
        return dateTime;
    }

    @Override
    public String toString() {
        return String.valueOf(dateTime);
    }
}
