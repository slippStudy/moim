package net.slipp.moim.domain.model.recruit;

import net.slipp.moim.domain.model.DomainTestSupport;
import net.slipp.moim.domain.model.user.UserId;
import org.mockito.Mock;

import static net.slipp.moim.domain.model.recruit.Recruit.Recruit;

public abstract class RecruitCommonTestSupport extends DomainTestSupport {

    @Mock
    protected RecruitRepository recruitRepository;

    protected RecruitId recruitId = RecruitId.of("1");
    protected UserId userId = UserId.of("test@gmail.com");

    void setUp() {
    }

    protected Recruit testRecruit() {
        return Recruit(recruitId, userId);
    }

    protected InquiryDefinition anyInquiryDefinition() {
        return new InquiryDefinition("너 몇살이니?", true);
    }
}
