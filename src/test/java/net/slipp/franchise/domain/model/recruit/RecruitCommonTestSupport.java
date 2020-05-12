package net.slipp.franchise.domain.model.recruit;

import net.slipp.franchise.domain.model.DomainTestSupport;
import org.mockito.Mock;

public abstract class RecruitCommonTestSupport extends DomainTestSupport {

    @Mock
    protected RecruitRepository recruitRepository;

    protected RecruitId recruitId = RecruitId.of("1");

    void setUp() {
    }

    protected Recruit testRecruit() {
        return new Recruit(recruitId);
    }

    protected InquiryDefinition anyInquiryDefinition() {
        return new InquiryDefinition("너 몇살이니?", true);
    }
}
