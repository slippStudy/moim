package net.slipp.franchise.domain.model.recruit;

import net.slipp.franchise.domain.model.meetup.MeetupId;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import static net.slipp.franchise.domain.model.recruit.Status.*;
import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.BDDMockito.given;

@ExtendWith(MockitoExtension.class)
class RecruitFactoryTest {

    private RecruitFactory dut;

    @Mock
    private RecruitIdGenerator recruitIdGenerator;
    private MeetupId meetupId = MeetupId.of("1");

    @BeforeEach
    void setUp() {
        dut = new RecruitFactory(recruitIdGenerator);
        given(recruitIdGenerator.gen()).willReturn(RecruitId.of("1"));

    }

    @Test
    @DisplayName("팩토리 테이스")
    void create() {
        Recruit actual = dut.create(meetupId);
        assertEquals(meetupId, actual.getMeetupId());
        assertEquals(RecruitId.of("1"), actual.getId());
        assertEquals(BEGIN, actual.getStatus());
    }
}