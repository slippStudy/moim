package net.slipp.ddd.events;

public class SpyDomainEventSubscriber implements DomainEventSubscriber {

    private DomainEvent publishedDomainEvent;
    private final Class<? extends DomainEvent> clazz;

    public SpyDomainEventSubscriber(Class<? extends DomainEvent> clazz) {
        this.clazz = clazz;
    }

    @Override
    public boolean support(DomainEvent anEvent) {
        return anEvent.getClass().isAssignableFrom(clazz);
    }

    @Override
    public void handle(DomainEvent anEvent) {
        this.publishedDomainEvent = anEvent;
    }

    public boolean handled() {
        return publishedDomainEvent != null;
    }

    public DomainEvent getPublishedDomainEvent() {
        return publishedDomainEvent;
    }

}
