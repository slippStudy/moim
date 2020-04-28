package net.slipp.franchise.domain.model.recruit;

import net.slipp.franchise.domain.model.DomainTestSupport;
import org.mockito.Mock;

import static org.mockito.BDDMockito.given;

public abstract class RecruitCommonTestSupport extends DomainTestSupport implements RecruitValueObjectSupport{

    @Mock
    protected RecruitIdGenerator recruitIdGenerator;
    protected RecruitFactory recruitFactory;

    public void setUp() {


        given(recruitIdGenerator.gen()).willReturn(RecruitId.of("1"));
        recruitFactory = new RecruitFactory(recruitIdGenerator);
    }
}
