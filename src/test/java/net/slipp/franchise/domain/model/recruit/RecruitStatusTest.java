/**
 * Created by joenggyu0@gmail.com on 4/21/20
 * Github : http://github.com/lenkim
 */

package net.slipp.franchise.domain.model.recruit;

import com.google.common.collect.Lists;
import net.slipp.franchise.domain.model.DomainTestSupport;
import net.slipp.franchise.domain.model.meetup.MeetupId;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;

import static net.slipp.franchise.domain.model.recruit.Status.FINISH;
import static net.slipp.franchise.domain.model.recruit.Status.START;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.BDDMockito.given;
import static org.mockito.MockitoAnnotations.initMocks;


public class RecruitStatusTest extends DomainTestSupport {

    private RecruitFactory factory;

    @Mock
    private RecruitIdGenerator recruitIdGenerator;
    private MeetupId meetupId = MeetupId.of("1");
    private Recruit dut;

    @Override
    @BeforeEach
    public void setUp() {

        super.setUp();

        given(recruitIdGenerator.gen()).willReturn(RecruitId.of("1"));
        factory = new RecruitFactory(recruitIdGenerator);
        dut = factory.create(meetupId);
    }

    @Test
    void beginToStart() {
        initMocks(this);
        dut.start();
        assertEquals(START, dut.getStatus());
    }


    @Test
    void startToStart() {
        dut.start();
        assertThrows(IllegalStateException.class, () -> {
            dut.start();
        });
    }

    @Test
    void beginToFinish() {
        assertThrows(IllegalStateException.class, () -> {
            dut.finish();
        });
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
        assertThrows(IllegalStateException.class, () -> {
            dut.finish();
        });
    }

    @Test
    void name() {
        assertEquals(Lists.newArrayList(RecruitStatusChangedEvent.class, RecruitCreatedEvent.class)
                , Lists.newArrayList(RecruitStatusChangedEvent.class, RecruitCreatedEvent.class)
        );
    }
}
