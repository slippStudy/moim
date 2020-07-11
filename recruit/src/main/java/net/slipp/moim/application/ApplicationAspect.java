package net.slipp.moim.application;

import net.slipp.ddd.domain.DomainEvent;
import net.slipp.ddd.domain.DomainEventPublisher;
import net.slipp.ddd.domain.DomainEventSubscriber;
import net.slipp.ddd.events.EventStore;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.springframework.stereotype.Component;

@Aspect
@Component
public class ApplicationAspect {

    private final EventStore eventStore;

    public ApplicationAspect(EventStore eventStore) {
        this.eventStore = eventStore;
    }


    @Before("@annotation(net.slipp.moim.application.Application)")
    public void listen(/*JoinPoint joinPoint*/) {
        DomainEventPublisher.instance().reset();
        DomainEventPublisher.instance().subscribe(new DomainEventSubscriber() {
            @Override
            public boolean support(DomainEvent anEvent) {
                //always
                return true;
            }

            @Override
            public void handle(DomainEvent anEvent) {
                eventStore.append(anEvent);
            }
        });
    }


}
