package net.slipp.franchise.domain.model.applicationform;

import net.slipp.franchise.domain.model.DomainTestSupport;
import net.slipp.franchise.domain.model.recruit.Recruit;
import net.slipp.franchise.domain.model.recruit.RecruitFactory;
import net.slipp.franchise.domain.model.recruit.RecruitId;
import net.slipp.franchise.domain.model.recruit.RecruitIdGenerator;
import org.mockito.Mock;

import static org.mockito.BDDMockito.given;

public abstract class ApplicationFormCommonTestSupport extends DomainTestSupport implements ApplicationFormValueObjectSupport {

    @Mock
    protected RecruitIdGenerator recruitIdGenerator;
    protected RecruitFactory recruitFactory;

    void setUp() {
        given(recruitIdGenerator.gen()).willReturn(RecruitId.of("1"));
        recruitFactory = new RecruitFactory(recruitIdGenerator);
    }

    protected Recruit getRecruit() {
        return recruitFactory.create(meetupId);
    }
}
