/**
 * Created by joenggyu0@gmail.com on 4/21/20
 * Github : http://github.com/lenkim
 */

package net.slipp.franchise.domain.model.recruit;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static net.slipp.franchise.domain.model.recruit.Status.FINISH;
import static net.slipp.franchise.domain.model.recruit.Status.START;
import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatIllegalStateException;


public class RecruitStatusTest extends RecruitCommonTestSupport {


    private Recruit dut;

    @Override
    @BeforeEach
    public void setUp() {

        super.setUp();

        dut = testRecruit();

        expectedEvents(1);
    }

    @Test
    void beginToStart() {
        dut.start();
        assertThat(dut.status()).isEqualTo(START);
        expectedEventInOrder(
            RecruitCreatedEvent.class,
            RecruitStatusChangedEvent.class
        );
        assertThat(domainEvent(RecruitCreatedEvent.class).getRecruitId()).isEqualTo(dut.id());
        assertThat(domainEvent(RecruitStatusChangedEvent.class).getRecruitId()).isEqualTo(dut.id());
        assertThat(domainEvent(RecruitStatusChangedEvent.class).getStatus()).isEqualTo(dut.status());
    }

    @Test
    void startToStart() {
        dut.start();
        assertThatIllegalStateException().isThrownBy(() -> dut.start());
    }

    @Test
    void beginToFinish() {
        assertThatIllegalStateException().isThrownBy(() -> dut.finish());
    }

    @Test
    void finish() {
        dut.start();
        dut.finish();
        assertThat(dut.status()).isEqualTo(FINISH);

        expectedEventInOrder(
                RecruitCreatedEvent.class,
                RecruitStatusChangedEvent.class,
                RecruitStatusChangedEvent.class);
    }

    @Test
    void finishToFinish() {
        dut.start();
        dut.finish();
        assertThatIllegalStateException().isThrownBy(() -> dut.finish());
    }

    @Test
    void finishToStart() {
        dut.start();
        dut.finish();

        assertThatIllegalStateException().isThrownBy(() -> dut.start());
    }
}
