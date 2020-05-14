package net.slipp.common.domain.model;

import org.junit.jupiter.api.extension.ExtendWith;

import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;

@ExtendWith(DomainEventResetExtension.class)
public interface DomainEventTestSupport {


    default void expectedEvents(int expectedCount) {
        assertThat(DomainEventPublisher.instance().getEventsClass()).hasSize(expectedCount);
    }

    default void expectedEventInOrder(Class<? extends DomainEvent>... anExpectedClass) {
        assertThat(DomainEventPublisher.instance().getEventsClass()).containsExactly(anExpectedClass);

    }

    default <T extends DomainEvent> T domainEvent(Class<T> aClass) {

        List<DomainEvent> actual = DomainEventPublisher.instance().allEvents();

        return (T) actual.stream().filter(it -> it.getClass().equals(aClass)).findFirst().orElse(null);

    }


}
