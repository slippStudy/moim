package net.slipp.franchise.domain.model.recruit;

import net.slipp.franchise.domain.model.DomainTestSupport;
import org.mockito.Mock;

import static org.mockito.BDDMockito.given;

public abstract class RecruitCommonTestSupport extends DomainTestSupport {

    @Mock
    protected RecruitIdGenerator recruitIdGenerator;
    protected RecruitFactory recruitFactory;

    @Override
    public void setUp() {

        super.setUp();

        given(recruitIdGenerator.gen()).willReturn(RecruitId.of("1"));
        recruitFactory = new RecruitFactory(recruitIdGenerator);
    }
}
