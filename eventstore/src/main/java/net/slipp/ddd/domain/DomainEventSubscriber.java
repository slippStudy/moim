package net.slipp.ddd.domain;

public interface DomainEventSubscriber {
    boolean support(DomainEvent anEvent);

    void handle(DomainEvent anEvent);
}
