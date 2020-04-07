/**
 * Created by joenggyu0@gmail.com on 4/7/20
 * Github : http://github.com/lenkim
 */

package net.slipp.franchise.domain.model.meetup;

import java.util.Objects;

public class MeetupId {

    private final String value;

    public static MeetupId of(String value){

        MeetupId meetupId = new MeetupId(value);

        return meetupId;
    }

    MeetupId(String value) {
        this.value = value;
    }

    @Override
    public String toString() {
        return String.valueOf(value);
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        MeetupId meetupId = (MeetupId) o;
        return Objects.equals(value, meetupId.value);
    }

    @Override
    public int hashCode() {
        return Objects.hash(value);
    }
}
