package net.slipp.moim.port.repository;

import net.slipp.ddd.events.StoredEvent;
import net.slipp.support.BaseRepositoryTest;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;

import java.time.LocalDateTime;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;

class MybatisStoredEventRepositoryTest extends BaseRepositoryTest {

    @Autowired
    private MybatisStoredEventRepository mybatisStoredEventRepository;

    @Test
    void saveAndSelectTest() {

        StoredEvent storedEvent = new StoredEvent("typeName", LocalDateTime.now(), "eventBody");

        mybatisStoredEventRepository.save(storedEvent);

        List<StoredEvent> result = mybatisStoredEventRepository.allStoredEventsSince(0);

        assertThat(result.get(0).eventId()).isEqualTo(1L);
    }
}
