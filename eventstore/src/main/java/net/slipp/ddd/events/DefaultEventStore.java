package net.slipp.ddd.events;


import net.slipp.ddd.domain.DomainEvent;

import java.util.List;

public class DefaultEventStore implements EventStore {

    private final StoredEventRepository storedEventRepository;

    public DefaultEventStore(StoredEventRepository storedEventRepository) {
        this.storedEventRepository = storedEventRepository;
    }

    @Override
    public List<StoredEvent> allStoredEventsBetween(long aLowStoredEventId, long aHighStoredEventId) {
        return storedEventRepository.allStoredEventsBetween(aLowStoredEventId, aHighStoredEventId);
    }

    @Override
    public List<StoredEvent> allStoredEventsSince(long aStoredEventId) {
        return storedEventRepository.allStoredEventsSince(aStoredEventId);
    }

    @Override
    public StoredEvent append(DomainEvent aDomainEvent) {
        return storedEventRepository.save(null);
    }

    @Override
    public void close() {
        // DO NOTHING
    }

    @Override
    public long countStoredEvents() {
        return storedEventRepository.countStoredEvents();
    }
}
