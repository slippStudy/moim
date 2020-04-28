package net.slipp.common.domain.model;

import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;

public interface DomainEventTestSupport {


    default void setUp() {
        DomainEventPublisher.instance().reset();
    }


    default void expectedEvents(int expectedCount) {
        assertEquals(expectedCount, DomainEventPublisher.instance().getEventsClass().size());
    }

    default  void expectedEventInOrder(Class<? extends DomainEvent>... anExpectedClass) {
        expectedEvents(anExpectedClass.length);

        List<Class<? extends DomainEvent>> actual = DomainEventPublisher.instance().getEventsClass();

        for (int i = 0; i < anExpectedClass.length; i++) {
            assertEquals(anExpectedClass[i], actual.get(i));
        }
    }
}
