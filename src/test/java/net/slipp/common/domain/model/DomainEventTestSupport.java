package net.slipp.common.domain.model;

import com.google.common.collect.Lists;
import org.junit.jupiter.api.extension.ExtendWith;

import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;

@ExtendWith(DomainEventResetExtension.class)
public interface DomainEventTestSupport  {


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

    default <T extends DomainEvent> T domainEvent(Class<T> aClass) {

        List<DomainEvent> actual = DomainEventPublisher.instance().allEvents();

        return (T) actual.stream().filter(it -> it.getClass().equals(aClass)).findFirst().orElse(null);

    }


}
