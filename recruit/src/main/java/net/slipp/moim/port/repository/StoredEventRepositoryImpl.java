package net.slipp.moim.port.repository;

import net.slipp.ddd.events.StoredEvent;
import net.slipp.ddd.events.StoredEventRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public class StoredEventRepositoryImpl implements StoredEventRepository {

    private MybatisStoredEventRepository mybatisStoredEventRepository;

    public StoredEventRepositoryImpl(MybatisStoredEventRepository mybatisStoredEventRepository) {
        this.mybatisStoredEventRepository = mybatisStoredEventRepository;
    }

    @Override
    public StoredEvent save(StoredEvent anEvent) {
        mybatisStoredEventRepository.save(anEvent);
        return mybatisStoredEventRepository.findByEventId(anEvent.eventId()).get();
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
        return mybatisStoredEventRepository.findByEventId(eventId);
    }
}
