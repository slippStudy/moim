package net.slipp.franchise.domain.model.recruit;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static net.slipp.franchise.domain.model.recruit.Status.BEGIN;
import static org.junit.jupiter.api.Assertions.assertEquals;

class RecruitFactoryTest extends RecruitCommonTestSupport {

    private RecruitFactory dut;


    @BeforeEach
    public void setUp() {
        super.setUp();
        dut = recruitFactory;
    }

    @Test
    @DisplayName("팩토리 테스트")
    void create() {
        Recruit actual = dut.create(meetupId);

        assertEquals(meetupId, actual.getMeetupId());
        assertEquals(recruitId, actual.getId());
        assertEquals(BEGIN, actual.getStatus());

        expectedEventInOrder(RecruitCreatedEvent.class);
    }


}