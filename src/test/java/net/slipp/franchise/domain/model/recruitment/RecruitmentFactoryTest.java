package net.slipp.franchise.domain.model.recruitment;

import net.slipp.franchise.domain.model.meetup.MeetupId;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import static net.slipp.franchise.domain.model.recruitment.Status.*;
import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.BDDMockito.given;

@ExtendWith(MockitoExtension.class)
class RecruitmentFactoryTest {

    private RecruitmentFactory dut;

    @Mock
    private RecruitmentIdGenerator recruitmentIdGenerator;
    private MeetupId meetupId = MeetupId.of("1");

    @BeforeEach
    void setUp() {
        dut = new RecruitmentFactory(recruitmentIdGenerator);
        given(recruitmentIdGenerator.gen()).willReturn(RecruitmentId.of("1"));

    }

    @Test
    @DisplayName("팩토리 테이스")
    void create() {
        Recruitment actual = dut.create(meetupId);
        assertEquals(meetupId, actual.getMeetupId());
        assertEquals(RecruitmentId.of("1"), actual.getId());
        assertEquals(BEGIN, actual.getStatus());
    }
}