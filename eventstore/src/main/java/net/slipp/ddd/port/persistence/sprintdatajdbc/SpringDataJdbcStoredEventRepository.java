package net.slipp.ddd.port.persistence.sprintdatajdbc;

import net.slipp.ddd.events.StoredEvent;
import net.slipp.ddd.events.StoredEventRepository;

import java.util.List;
import java.util.Optional;

public class SpringDataJdbcStoredEventRepository implements StoredEventRepository {
    @Override
    public StoredEvent save(StoredEvent anEvent) {
        return null;
    }

    @Override
    public List<StoredEvent> allStoredEventsBetween(long aLowStoredEventId, long aHighStoredEventId) {
        return null;
    }

    @Override
    public List<StoredEvent> allStoredEventsSince(long aStoredEventId) {
        return null;
    }

    @Override
    public long countStoredEvents() {
        return 0;
    }

    @Override
    public Optional<StoredEvent> findByEventId(Long eventId) {
        return Optional.empty();
    }
}
