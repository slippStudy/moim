package net.slipp.moim.application;

import net.slipp.common.domain.model.DomainEventTestSupport;
import net.slipp.moim.application.service.RecruitApplicationService;
import net.slipp.moim.domain.model.recruit.Recruit;
import net.slipp.moim.domain.model.recruit.RecruitId;
import net.slipp.moim.domain.model.recruit.RecruitRepository;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

@ExtendWith(MockitoExtension.class)
public class ApplicationTest implements DomainEventTestSupport {

    @InjectMocks
    protected RecruitApplicationService dut;

    @Mock
    protected RecruitRepository recruitRepository;

    public ApplicationTest() {
    }

    protected Recruit RecruitAggregate(){
        RecruitId id = RecruitId.of("1");
        return Recruit.Recruit(id);
    }
}
