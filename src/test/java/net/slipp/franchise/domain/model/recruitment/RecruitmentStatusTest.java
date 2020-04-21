/**
 * Created by joenggyu0@gmail.com on 4/21/20
 * Github : http://github.com/lenkim
 */

package net.slipp.franchise.domain.model.recruitment;

import net.slipp.franchise.domain.model.meetup.MeetupId;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import static net.slipp.franchise.domain.model.recruitment.Status.FINISH;
import static net.slipp.franchise.domain.model.recruitment.Status.START;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.BDDMockito.given;

@ExtendWith(MockitoExtension.class)
public class RecruitmentStatusTest {

    private RecruitmentFactory factory;

    @Mock
    private RecruitmentIdGenerator recruitmentIdGenerator;
    private MeetupId meetupId = MeetupId.of("1");
    private Recruitment dut;

    @BeforeEach
    void setUp() {
        given(recruitmentIdGenerator.gen()).willReturn(RecruitmentId.of("1"));
        factory = new RecruitmentFactory(recruitmentIdGenerator);
        dut = factory.create(meetupId);
    }


    @Test
    void beginToStart() {
        dut.start();
        assertEquals(START, dut.getStatus());
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
