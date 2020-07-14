package net.slipp.moim.application;

import net.slipp.moim.application.service.RecruitApplicationService;
import net.slipp.moim.domain.model.recruit.Recruit;
import net.slipp.moim.domain.model.recruit.RecruitId;
import net.slipp.moim.domain.model.recruit.RecruitRepository;
import org.mockito.InjectMocks;
import org.mockito.Mock;

public class ApplicationTest {

    @InjectMocks
    protected RecruitApplicationService recruitApplicationService;

    @Mock
    protected RecruitRepository recruitRepository;

    public ApplicationTest() {
    }

    protected Recruit RecruitAggregate(){
        RecruitId id = RecruitId.of("1");
        return Recruit.Recruit(id);
    }
}
