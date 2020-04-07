package net.slipp.franchise.domain.model.recruitment;

import net.slipp.franchise.domain.model.meetup.MeetupId;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import static org.mockito.BDDMockito.given;

@ExtendWith(MockitoExtension.class)
class RecruitmentFactoryTest {

    private RecruitmentFactory dut;

    @Mock
    private RecruitmentIdGenerator recruitmentIdGenerator;

    @BeforeEach
    void setUp() {
        dut = new RecruitmentFactory(recruitmentIdGenerator);
        given(recruitmentIdGenerator.gen()).willReturn(RecruitmentId.of("1"));

    }

    @Test
    @DisplayName("팩토리 테이스")
    void name() {
        MeetupId meetupId = MeetupId.of("1");
        Recruitment actual = dut.create(meetupId);
        Assertions.assertEquals(meetupId, actual.getMeetupId());
        Assertions.assertEquals(RecruitmentId.of("1"), actual.getId());
        Assertions.assertEquals(RecruitmentStatus.BEGIN, actual.getStatus());

    }
}