package net.slipp.common.domain.model;

import com.google.common.collect.Lists;

import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;

public interface DomainEventTestSupport {


    default void setUp() {
        DomainEventPublisher.instance().reset();
    }


    default void expectedEvents(int expectedCount) {
        assertEquals(expectedCount, DomainEventPublisher.instance().getEventsClass().size());
    }

    default  void expectedEventInOrder(Class<? extends DomainEvent> anExpectedClass) {
        expectedEventInOrder(Lists.newArrayList(anExpectedClass));
    }

    default  void expectedEventInOrder(Class<? extends DomainEvent> anExpectedClass1,Class<? extends DomainEvent> anExpectedClass2) {
        expectedEventInOrder(Lists.newArrayList(anExpectedClass1, anExpectedClass2));
    }

    default  void expectedEventInOrder(Class<? extends DomainEvent> anExpectedClass1,Class<? extends DomainEvent> anExpectedClass2,Class<? extends DomainEvent> anExpectedClass3) {
        expectedEventInOrder(Lists.newArrayList(anExpectedClass1, anExpectedClass2, anExpectedClass3));
    }

    default  void expectedEventInOrder(List<Class<? extends DomainEvent>> anExpectedClassList) {
        expectedEvents(anExpectedClassList.size());

        List<Class<? extends DomainEvent>> actual = DomainEventPublisher.instance().getEventsClass();

        for (int i = 0; i < anExpectedClassList.size(); i++) {
            assertEquals(anExpectedClassList.get(i), actual.get(i));
        }
    }
}
