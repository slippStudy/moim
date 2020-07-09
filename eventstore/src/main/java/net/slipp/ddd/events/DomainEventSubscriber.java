package net.slipp.ddd.events;

public interface DomainEventSubscriber {
    boolean support(DomainEvent anEvent);

    void handle(DomainEvent anEvent);
}
