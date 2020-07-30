package net.slipp.ddd.events;


import net.slipp.ddd.domain.DomainEvent;

import java.util.List;

public class DefaultEventStore implements EventStore {

    private final StoredEventRepository storedEventRepository;

    public DefaultEventStore(StoredEventRepository storedEventRepository) {
        this.storedEventRepository = storedEventRepository;
    }

    @Override
    public StoredEvent append(DomainEvent aDomainEvent) {
        return storedEventRepository.save(null);
    }

    @Override
    public void close() {
        // DO NOTHING
    }

}
