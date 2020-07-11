package net.slipp.ddd.events;

import net.slipp.ddd.domain.DomainEvent;

import java.util.List;

public interface EventStore {

    List<StoredEvent> allStoredEventsBetween(long aLowStoredEventId, long aHighStoredEventId);

    List<StoredEvent> allStoredEventsSince(long aStoredEventId);

    StoredEvent append(DomainEvent aDomainEvent);

    void close();

    long countStoredEvents();
}
