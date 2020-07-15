package net.slipp.moim.domain.model.recruit;

import net.slipp.ddd.domain.SpyDomainEventSubscriber;
import net.slipp.ddd.domain.DomainEventPublisher;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.time.LocalDateTime;

import static net.slipp.moim.domain.model.recruit.Content.NO_CONTENT;
import static net.slipp.moim.domain.model.recruit.Recruit.Recruit;
import static net.slipp.moim.domain.model.recruit.Status.BEGIN;
import static net.slipp.moim.domain.model.recruit.Title.UNTITLED;
import static org.assertj.core.api.Assertions.*;

class RecruitTest extends RecruitCommonTestSupport {


    SpyDomainEventSubscriber aSpySubscriber1;
    SpyDomainEventSubscriber aSpySubscriber2;

    @Override
    @BeforeEach
    public void setUp() {

        super.setUp();

        aSpySubscriber1 = new SpyDomainEventSubscriber(RecruitCreatedEvent.class);
        aSpySubscriber2 = new SpyDomainEventSubscriber(RecruitStatusChangedEvent.class);

        DomainEventPublisher.instance().subscribe(aSpySubscriber1);
        DomainEventPublisher.instance().subscribe(aSpySubscriber2);

    }

    @Test
    @DisplayName("기본 생성자")
    void create() {


        assertThatIllegalStateException().isThrownBy(() -> Recruit(null));
        assertThatIllegalStateException().isThrownBy(() -> Recruit(null));

        Recruit recruit = Recruit(recruitId);

        assertThat(recruit.id()).isEqualTo(recruitId);
        assertThat(recruit.title()).isEqualTo(UNTITLED);
        assertThat(recruit.content()).isEqualTo(NO_CONTENT);
        assertThat(recruit.status()).isEqualTo(BEGIN);
        assertThat(recruit.deadLineDateTime().dateTime().toLocalDate()).isEqualTo(LocalDateTime.now().toLocalDate());

        assertThat(recruit.allInquiryDefinitions()).isEmpty();

        assertThat(aSpySubscriber1.getPublishedDomainEvent())
                .isInstanceOf(RecruitCreatedEvent.class)
                .hasFieldOrPropertyWithValue("recruitId", recruitId);

    }


    @Test
    void addInquiryItem() {

        Recruit dut = testRecruit();

        assertThat(dut.allInquiryDefinitions()).isEmpty();
        assertThatIllegalArgumentException().isThrownBy(() -> dut.addInquiryDefinition(null));

        dut.addInquiryDefinition(anyInquiryDefinition());

        assertThat(dut.allInquiryDefinitions()).hasSize(1);

        dut.start();

        //TODO 삭제 필요
        assertThat(aSpySubscriber1.handled()).isEqualTo(true);
        assertThat(aSpySubscriber1.getPublishedDomainEvent()).hasFieldOrPropertyWithValue("recruitId", recruitId);

        assertThat(aSpySubscriber2.handled()).isEqualTo(true);
        assertThat(aSpySubscriber2.getPublishedDomainEvent()).hasFieldOrPropertyWithValue("recruitId", recruitId);


        assertThatIllegalStateException().isThrownBy(() -> dut.addInquiryDefinition(anyInquiryDefinition()));
    }

}
