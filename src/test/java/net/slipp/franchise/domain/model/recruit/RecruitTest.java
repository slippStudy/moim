package net.slipp.franchise.domain.model.recruit;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static net.slipp.franchise.domain.model.recruit.Status.BEGIN;
import static org.junit.jupiter.api.Assertions.*;

class RecruitTest extends RecruitCommonTestSupport {

    Recruit dut;


    @BeforeEach
    void setUp() {
        super.setUp();
        dut = testRecruit();
    }

    @Test
    @DisplayName("팩토리 테스트")
    void create() {
        assertEquals(recruitId, dut.getId());
        assertEquals(BEGIN, dut.getStatus());

        expectedEventInOrder(RecruitCreatedEvent.class);
    }


    @Test
    void addInquiryItem() {

        assertTrue(dut.allInquiryDefinitions().isEmpty());

        assertThrows(IllegalArgumentException.class , () -> dut.addInquiryDefinition(null));

        dut.addInquiryDefinition(anyInquiryDefinition());

        assertEquals(1, dut.allInquiryDefinitions().size());

        dut.start();

        expectedEventInOrder(
            RecruitCreatedEvent.class,
            RecruitStatusChangedEvent.class);

        assertThrows(IllegalStateException.class, () -> dut.addInquiryDefinition(anyInquiryDefinition()));
    }

}
