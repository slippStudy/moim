package net.slipp.ddd.events;

import java.util.List;
import java.util.Optional;

public interface StoredEventRepository {

    StoredEvent save(StoredEvent anEvent);

    List<StoredEvent> allStoredEventsBetween(long aLowStoredEventId, long aHighStoredEventId);

    List<StoredEvent> allStoredEventsSince(long aStoredEventId);

    long countStoredEvents();

    Optional<StoredEvent> findByEventId(Long eventId);
}
