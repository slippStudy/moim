package net.slipp.moim.port.repository;

import net.slipp.ddd.events.StoredEvent;
import net.slipp.support.BaseRepositoryTest;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;

import java.time.LocalDateTime;
import java.util.UUID;

import static org.assertj.core.api.Assertions.assertThat;

class MybatisStoredEventRepositoryTest extends BaseRepositoryTest {

    @Autowired
    private MybatisStoredEventRepository mybatisStoredEventRepository;

    private StoredEvent storedEvent;
    private String eventId;

    @BeforeEach
    void beforeEach() {

        eventId = UUID.randomUUID().toString();
        storedEvent = new StoredEvent(eventId, "typeName", LocalDateTime.now(), "eventBody");
    }

    @Test
    void save() {

        StoredEvent saved = mybatisStoredEventRepository.save(storedEvent);
        assertThat(saved.eventId()).isEqualTo(eventId);
    }

}
