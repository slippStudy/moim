package net.slipp.moim.application;


import net.slipp.ddd.domain.DomainEvent;
import net.slipp.ddd.domain.DomainEventPublisher;
import net.slipp.ddd.events.EventStore;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.EnableAspectJAutoProxy;
import org.springframework.test.context.junit.jupiter.SpringJUnitConfig;

import java.time.LocalDateTime;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.verify;

@SpringJUnitConfig(classes = ApplicationAspectTest.Config.class)
class ApplicationAspectTest {


    @Configuration
    @EnableAspectJAutoProxy
    static class Config {

        @Bean
        public EventStore eventStore () {
            return mock(EventStore.class);
        }

        @Bean
        public ApplicationAspect applicationAspect(EventStore eventStore){
            return new ApplicationAspect(eventStore);
        }

        @Bean
        public FooService fooService () {
            return new FooService();
        }
    }

    static class FooService {
        @Application
        public void foo() {
            DomainEventPublisher.instance().publish(new FooDomainEvent());
        }

    }

    static class FooDomainEvent implements DomainEvent {

        @Override
        public LocalDateTime occurredOn() {
            return null;
        }
    }


    @Autowired FooService fooService;

    @Autowired EventStore eventStore;

    @Test
    void listen() {

        assertThat(fooService).isNotNull();
        assertThat(eventStore).isNotNull();

        fooService.foo();

        verify(eventStore).append(any(FooDomainEvent.class));
    }
}