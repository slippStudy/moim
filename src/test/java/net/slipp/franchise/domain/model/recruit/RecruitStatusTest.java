/**
 * Created by joenggyu0@gmail.com on 4/21/20
 * Github : http://github.com/lenkim
 */

package net.slipp.franchise.domain.model.recruit;

import net.slipp.common.domain.model.DomainEvent;
import net.slipp.common.domain.model.DomainEventPublisher;
import net.slipp.franchise.domain.model.meetup.MeetupId;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.List;

import static net.slipp.franchise.domain.model.recruit.Status.FINISH;
import static net.slipp.franchise.domain.model.recruit.Status.START;
import static org.hamcrest.Matchers.arrayContaining;
import static org.hamcrest.Matchers.is;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.BDDMockito.given;
import static org.mockito.MockitoAnnotations.initMocks;

@ExtendWith(MockitoExtension.class)
public class RecruitStatusTest {

    private RecruitFactory factory;

    @Mock
    private RecruitIdGenerator recruitIdGenerator;
    private MeetupId meetupId = MeetupId.of("1");
    private Recruit dut;

    @BeforeEach
    void setUp() {

        DomainEventPublisher.instance().reset();

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

    private void expectedEvents(int expectedCount) {
        assertEquals(expectedCount, DomainEventPublisher.instance().getEventsClass().size());
    }

    @SafeVarargs
    private final void expectedEvent(Class<? extends DomainEvent>... anExpectedClass) {
        List<Class<? extends DomainEvent>> actuals = DomainEventPublisher.instance().getEventsClass();
        org.hamcrest.MatcherAssert.assertThat(anExpectedClass, is(arrayContaining(actuals)));
//
    }

    @Test
    void startToStart() {
        dut.start();
        assertThrows(IllegalStateException.class,() -> {
            dut.start();
        });
    }

    @Test
    void beginToFinish() {
        assertThrows(IllegalStateException.class,() -> {
            dut.finish();
        });
    }

    @Test

    void finish() {
        dut.start();
        dut.finish();
        assertEquals(FINISH, dut.getStatus());
        expectedEvents(3);

        expectedEvent(RecruitCreatedEvent.class,
                RecruitStatusChangedEvent.class,
                RecruitStatusChangedEvent.class);
    }

    @Test
    void finishToFinish() {
        dut.start();
        dut.finish();
        assertThrows(IllegalStateException.class,() -> {
            dut.finish();
        });
    }
}
