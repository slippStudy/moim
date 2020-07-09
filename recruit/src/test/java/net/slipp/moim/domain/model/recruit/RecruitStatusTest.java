/**
 * Created by joenggyu0@gmail.com on 4/21/20
 * Github : http://github.com/lenkim
 */

package net.slipp.moim.domain.model.recruit;

import net.slipp.ddd.events.DomainEventPublisher;
import net.slipp.ddd.events.SpyDomainEventSubscriber;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static net.slipp.moim.domain.model.recruit.Status.FINISH;
import static net.slipp.moim.domain.model.recruit.Status.START;
import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatIllegalStateException;


public class RecruitStatusTest extends RecruitCommonTestSupport {


    private Recruit dut;

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

        dut = testRecruit();

        assertThat(aSpySubscriber1.getPublishedDomainEvent()).isInstanceOf(RecruitCreatedEvent.class);
    }

    @Test
    void beginToStart() {
        dut.start();

        assertThat(dut.status()).isEqualTo(START);

        //TODO order test
        assertThat(aSpySubscriber1.getPublishedDomainEvent()).isInstanceOf(RecruitCreatedEvent.class);

        assertThat(aSpySubscriber2.getPublishedDomainEvent())
                .isInstanceOf(RecruitStatusChangedEvent.class)
                .hasFieldOrPropertyWithValue("recruitId", recruitId)
                .hasFieldOrPropertyWithValue("status", START);

    }

    @Test
    void startToStart() {
        dut.start();
        assertThatIllegalStateException().isThrownBy(() -> dut.start());
    }

    @Test
    void beginToFinish() {
        assertThatIllegalStateException().isThrownBy(() -> dut.finish());
    }

    @Test
    void finish() {
        dut.start();
        dut.finish();
        assertThat(dut.status()).isEqualTo(FINISH);

        assertThat(aSpySubscriber1.getPublishedDomainEvent()).isInstanceOf(RecruitCreatedEvent.class);

        assertThat(aSpySubscriber2.getPublishedDomainEvent())
                .isInstanceOf(RecruitStatusChangedEvent.class)
                .hasFieldOrPropertyWithValue("recruitId", recruitId)
                .hasFieldOrPropertyWithValue("status", FINISH);
    }

    @Test
    void finishToFinish() {
        dut.start();
        dut.finish();
        assertThatIllegalStateException().isThrownBy(() -> dut.finish());
    }

    @Test
    void finishToStart() {
        dut.start();
        dut.finish();

        assertThatIllegalStateException().isThrownBy(() -> dut.start());
    }
}
