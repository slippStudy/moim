package net.slipp.franchise.domain.model.recruit;

import net.slipp.franchise.domain.model.DomainTestSupport;
import net.slipp.franchise.domain.model.meetup.MeetupId;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;

import static net.slipp.franchise.domain.model.recruit.Status.BEGIN;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.BDDMockito.given;

class RecruitFactoryTest extends DomainTestSupport {

    private RecruitFactory dut;

    @Mock
    private RecruitIdGenerator recruitIdGenerator;
    private MeetupId meetupId = MeetupId.of("1");


    @BeforeEach
    public void setUp() {
        super.setUp();
        dut = new RecruitFactory(recruitIdGenerator);
        given(recruitIdGenerator.gen()).willReturn(RecruitId.of("1"));

    }

    @Test
    @DisplayName("팩토리 테스트")
    void create() {
        Recruit actual = dut.create(meetupId);

        assertEquals(meetupId, actual.getMeetupId());
        assertEquals(RecruitId.of("1"), actual.getId());
        assertEquals(BEGIN, actual.getStatus());

        expectedEventInOrder(RecruitCreatedEvent.class);
    }


}