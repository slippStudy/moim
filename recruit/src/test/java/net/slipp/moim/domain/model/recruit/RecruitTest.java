package net.slipp.moim.domain.model.recruit;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.time.LocalDateTime;

import static net.slipp.moim.domain.model.recruit.Content.NO_CONTENT;
import static net.slipp.moim.domain.model.recruit.Recruit.Recruit;
import static net.slipp.moim.domain.model.recruit.Status.BEGIN;
import static net.slipp.moim.domain.model.recruit.Title.UNTITLED;
import static org.assertj.core.api.Assertions.*;

class RecruitTest extends RecruitCommonTestSupport {

    @Test
    @DisplayName("기본 생성자")
    void create() {

        assertThatIllegalStateException().isThrownBy(() -> Recruit(null, null));
        assertThatIllegalStateException().isThrownBy(() -> Recruit(null, userId));
        assertThatIllegalStateException().isThrownBy(() -> Recruit(recruitId, null));

        Recruit recruit = Recruit(recruitId, userId);

        assertThat(recruit.id()).isEqualTo(recruitId);
        assertThat(recruit.owner()).isEqualTo(userId);
        assertThat(recruit.title()).isEqualTo(UNTITLED);
        assertThat(recruit.content()).isEqualTo(NO_CONTENT);
        assertThat(recruit.status()).isEqualTo(BEGIN);
        assertThat(recruit.deadLineDateTime().dateTime().toLocalDate()).isEqualTo(LocalDateTime.now().toLocalDate());

        assertThat(recruit.allInquiryDefinitions()).isEmpty();

        expectedEventInOrder(RecruitCreatedEvent.class);

        assertThat(domainEvent(RecruitCreatedEvent.class).getRecruitId()).isEqualTo(recruit.id());

    }


    @Test
    void addInquiryItem() {

        Recruit dut = testRecruit();

        assertThat(dut.allInquiryDefinitions()).isEmpty();
        assertThatIllegalArgumentException().isThrownBy(() -> dut.addInquiryDefinition(null));

        dut.addInquiryDefinition(anyInquiryDefinition());

        assertThat(dut.allInquiryDefinitions()).hasSize(1);

        dut.start();

        expectedEventInOrder(
            RecruitCreatedEvent.class,
            RecruitStatusChangedEvent.class);

        assertThatIllegalStateException().isThrownBy(() -> dut.addInquiryDefinition(anyInquiryDefinition()));
    }

}
