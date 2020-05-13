package net.slipp.franchise.domain.model.recruit;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static net.slipp.franchise.domain.model.recruit.Recruit.Recruit;
import static net.slipp.franchise.domain.model.recruit.Status.BEGIN;
import static org.junit.jupiter.api.Assertions.*;

class RecruitTest extends RecruitCommonTestSupport {

    @Test
    @DisplayName("기본 생성자")
    void create() {

        assertThrows(IllegalStateException.class, () -> Recruit(null));

        Recruit recruit = Recruit(recruitId);

        assertEquals(recruitId, recruit.id());
        assertEquals(Title.UNTITLED, recruit.title());
        assertEquals(Content.NO_CONTENT, recruit.content());
        assertEquals(BEGIN, recruit.status());
        assertTrue(recruit.allInquiryDefinitions().isEmpty());

        expectedEventInOrder(RecruitCreatedEvent.class);
        assertEquals(recruit.id(), domainEvent(RecruitCreatedEvent.class).getRecruitId());

    }


    @Test
    void addInquiryItem() {

        Recruit dut = testRecruit();
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
