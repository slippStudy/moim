package net.slipp;


import net.slipp.ddd.events.DefaultEventStore;
import net.slipp.ddd.events.EventStore;
import net.slipp.ddd.events.StoredEvent;
import net.slipp.ddd.events.StoredEventRepository;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.liquibase.LiquibaseProperties;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.EnableAspectJAutoProxy;

import java.util.List;
import java.util.Optional;

@SpringBootApplication
@EnableConfigurationProperties({ LiquibaseProperties.class })
public class TestApp {

    public static void main(String[] args) {
        SpringApplication.run(TestApp.class, args);
    }


    @EnableAspectJAutoProxy
    @Configuration
    static class AopConfiguration {
    }

    @Configuration
    static class DomainEventConfiguration {
        @Bean
        public StoredEventRepository storedEventRepository() {
            return new StoredEventRepository() {
                @Override
                public StoredEvent save(StoredEvent anEvent) {
                    return null;
                }

                @Override
                public List<StoredEvent> allStoredEventsBetween(long aLowStoredEventId, long aHighStoredEventId) {
                    throw new UnsupportedOperationException();
                }

                @Override
                public List<StoredEvent> allStoredEventsSince(long aStoredEventId) {
                    throw new UnsupportedOperationException();
                }

                @Override
                public long countStoredEvents() {
                    throw new UnsupportedOperationException();
                }

                @Override
                public Optional<StoredEvent> findByEventId(Long eventId) {
                    throw new UnsupportedOperationException();
                }
            };
        }

        @Bean
        public EventStore eventStore(StoredEventRepository storedEventRepository) {
            return new DefaultEventStore(storedEventRepository);
        }
    }
}
