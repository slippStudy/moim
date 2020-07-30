package net.slipp.ddd.events;

public interface StoredEventRepository {

    StoredEvent save(StoredEvent anEvent);

}
