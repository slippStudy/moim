/**
 * Created by joenggyu0@gmail.com on 4/7/20
 * Github : http://github.com/lenkim
 */

package net.slipp.franchise.domain.model.recruit;

import java.util.Objects;

public class RecruitId {

    private final String value;

    public static RecruitId of(String value) {
        RecruitId RecruitId = new RecruitId(value);
        return RecruitId;
    }

    RecruitId(String value) {
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
        RecruitId RecruitId = (RecruitId) o;
        return Objects.equals(value, RecruitId.value);
    }

    @Override
    public int hashCode() {
        return Objects.hash(value);
    }
}
