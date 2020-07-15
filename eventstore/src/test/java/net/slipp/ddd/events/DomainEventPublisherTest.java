package net.slipp.ddd.events;

import net.slipp.ddd.domain.DomainEvent;
import net.slipp.ddd.domain.DomainEventPublisher;
import net.slipp.ddd.domain.DomainEventSubscriber;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.time.LocalDateTime;

import static org.mockito.BDDMockito.given;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class DomainEventPublisherTest {

    @Mock
    DomainEvent domainEvent;

    @Mock
    DomainEventSubscriber subscriber1;

    @Mock
    DomainEventSubscriber subscriber2;

    @BeforeEach
    void setUp() {
    }

    @Test
    void publishAndSubscribeOnSameThread() {
        given(subscriber1.support(domainEvent)).willReturn(true);
        given(subscriber2.support(domainEvent)).willReturn(false);

        DomainEventPublisher.instance().subscribe(subscriber1);
        DomainEventPublisher.instance().subscribe(subscriber2);

        DomainEventPublisher.instance().publish(domainEvent);

        verify(subscriber1).handle(domainEvent);
        verify(subscriber2, never()).handle(domainEvent);
    }


    @Test
    void publishAndSubscribeOnEachThread() {

        new Thread(() -> {
            DomainEventPublisher.instance().subscribe(subscriber1);
            DomainEventPublisher.instance().subscribe(subscriber2);
        }).start();

        DomainEventPublisher.instance().publish(domainEvent);

        verify(subscriber1, never()).support(domainEvent);
        verify(subscriber2, never()).support(domainEvent);
    }

    static class FooEvent extends StubEvent {
    }

    static class BarEvent extends StubEvent {
    }

    abstract static class StubEvent implements DomainEvent {

        @Override
        public LocalDateTime occurredOn() {
            return null;
        }
    }

    class FooEventSubscriber implements DomainEventSubscriber {

        @Override
        public boolean support(DomainEvent anEvent) {
            return anEvent instanceof FooEvent;
        }

        @Override
        public void handle(DomainEvent anEvent) {

        }
    }

    class BarEventSubscriber implements DomainEventSubscriber {

        @Override
        public boolean support(DomainEvent anEvent) {
            return anEvent instanceof BarEvent;
        }

        @Override
        public void handle(DomainEvent anEvent) {

        }
    }

    @Test
    void nestedPublishBlocked() {
        BarEvent barEvent = new BarEvent();
        DomainEventPublisher.instance().subscribe(new FooEventSubscriber() {
            @Override
            public void handle(DomainEvent anEvent) {
                // this is nested publish!!!
                DomainEventPublisher.instance().publish(barEvent);
            }
        });

        DomainEventSubscriber barSubscriber = spy(new BarEventSubscriber());
        DomainEventPublisher.instance().subscribe(barSubscriber);


        DomainEventPublisher.instance().publish(new FooEvent());

        verify(barSubscriber, never()).handle(barEvent);

    }
}
