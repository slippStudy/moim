package net.slipp.moim.application.service;

import net.slipp.ddd.events.EventStore;
import net.slipp.moim.application.ApplicationAspect;
import net.slipp.moim.domain.model.recruit.RecruitCreatedEvent;
import net.slipp.moim.domain.model.recruit.RecruitId;
import net.slipp.moim.domain.model.recruit.RecruitRepository;
import org.junit.jupiter.api.Test;
import org.mockito.ArgumentCaptor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.EnableAspectJAutoProxy;
import org.springframework.test.context.junit.jupiter.SpringJUnitConfig;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.BDDMockito.given;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.verify;

@SpringJUnitConfig(classes = RecruitApplicationServiceTest.Config.class)
class RecruitApplicationServiceTest {

    @Autowired
    EventStore eventStore;

    @Autowired
    RecruitApplicationService dut;

    @Autowired
    RecruitRepository recruitRepository;

    @Test
    void changeRecruitTest() {
        given(recruitRepository.nextId()).willReturn(RecruitId.of("1234"));
        ArgumentCaptor<RecruitCreatedEvent> captor = ArgumentCaptor.forClass(RecruitCreatedEvent.class);

        dut.createRecruit();

        verify(eventStore).append(captor.capture());

        RecruitCreatedEvent event = captor.getValue();

        assertThat(event.getRecruitId()).isEqualTo(RecruitId.of("1234"));
    }

    @Test
    void editChangeTest() {
    }

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
        public RecruitApplicationService dut() {
            return new RecruitApplicationService(recruitRepository());
        }

        @Bean
        public RecruitRepository recruitRepository() {
            return mock(RecruitRepository.class);
        }
    }
}