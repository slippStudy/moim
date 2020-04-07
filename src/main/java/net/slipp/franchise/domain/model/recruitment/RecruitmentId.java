/**
 * Created by joenggyu0@gmail.com on 4/7/20
 * Github : http://github.com/lenkim
 */

package net.slipp.franchise.domain.model.recruitment;

import java.util.Objects;

public class RecruitmentId {

    private final String value;

    public static RecruitmentId of(String value) {
        RecruitmentId RecruitmentId = new RecruitmentId(value);
        return RecruitmentId;
    }

    RecruitmentId(String value) {
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
        RecruitmentId RecruitmentId = (RecruitmentId) o;
        return Objects.equals(value, RecruitmentId.value);
    }

    @Override
    public int hashCode() {
        return Objects.hash(value);
    }
}
