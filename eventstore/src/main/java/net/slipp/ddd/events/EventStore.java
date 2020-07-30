package net.slipp.ddd.events;

import net.slipp.ddd.domain.DomainEvent;

import java.util.List;

public interface EventStore {

    StoredEvent append(DomainEvent aDomainEvent);

    void close();
}
