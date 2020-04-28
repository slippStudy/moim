/**
 * Created by joenggyu0@gmail.com on 4/7/20
 * Github : http://github.com/lenkim
 */

package net.slipp.franchise.domain.model.recruit;

import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Getter;
import net.slipp.franchise.domain.model.meetup.MeetupId;

import javax.validation.constraints.NotNull;

import static net.slipp.franchise.domain.model.recruit.Status.*;

@Getter
@AllArgsConstructor(access = AccessLevel.PACKAGE)
public class Recruit {

    @NotNull
    private final RecruitId id;

    @NotNull
    private final MeetupId meetupId;

    @NotNull
    private Status status;

    public void start() {
        if (BEGIN != getStatus()) {
            throw new IllegalStateException();
        }
        setStatus(START);

    }

    public void finish() {
        // TODO 상태 전이 책임을 다르 곳을 옮겨 주세요
        if (START != getStatus()) {
            throw new IllegalStateException();
        }
        setStatus(FINISH);
    }

    private void setStatus(@NotNull final Status status) {
        this.status = status;
    }
}
