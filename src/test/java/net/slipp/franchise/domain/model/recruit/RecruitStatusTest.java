/**
 * Created by joenggyu0@gmail.com on 4/21/20
 * Github : http://github.com/lenkim
 */

package net.slipp.franchise.domain.model.recruit;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static net.slipp.franchise.domain.model.recruit.Status.FINISH;
import static net.slipp.franchise.domain.model.recruit.Status.START;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;


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
        assertEquals(START, dut.getStatus());
        expectedEventInOrder(
            RecruitCreatedEvent.class,
            RecruitStatusChangedEvent.class
        );
        assertEquals(dut.getId(), domainEvent(RecruitCreatedEvent.class).getRecruitId());
        assertEquals(dut.getId(), domainEvent(RecruitStatusChangedEvent.class).getRecruitId());
        assertEquals(dut.getStatus(), domainEvent(RecruitStatusChangedEvent.class).getStatus());
    }

    @Test
    void startToStart() {
        dut.start();
        assertThrows(IllegalStateException.class, () -> dut.start());
    }

    @Test
    void beginToFinish() {
        assertThrows(IllegalStateException.class, () -> dut.finish());
    }

    @Test
    void finish() {
        dut.start();
        dut.finish();
        assertEquals(FINISH, dut.getStatus());

        expectedEventInOrder(
                RecruitCreatedEvent.class,
                RecruitStatusChangedEvent.class,
                RecruitStatusChangedEvent.class);
    }

    @Test
    void finishToFinish() {
        dut.start();
        dut.finish();
        assertThrows(IllegalStateException.class, () -> dut.finish());
    }

    @Test
    void finishToStart() {
        dut.start();
        dut.finish();

        assertThrows(IllegalStateException.class, ()-> dut.start());
    }
}
