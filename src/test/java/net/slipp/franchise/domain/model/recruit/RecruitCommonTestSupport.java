package net.slipp.franchise.domain.model.recruit;

import net.slipp.franchise.domain.model.DomainTestSupport;
import net.slipp.franchise.domain.model.recruit.inqueryitem.InquiryFactory;
import net.slipp.franchise.domain.model.recruit.inqueryitem.InquiryGenerator;
import net.slipp.franchise.domain.model.recruit.inqueryitem.InquiryItem;
import net.slipp.franchise.domain.model.recruit.inqueryitem.InquiryItemId;
import org.mockito.Mock;

import static org.mockito.BDDMockito.given;

public abstract class RecruitCommonTestSupport extends DomainTestSupport implements RecruitValueObjectSupport {

    @Mock
    protected RecruitIdGenerator recruitIdGenerator;

//    @Mock
//    protected InquiryGenerator inquiryGenerator;

    protected RecruitFactory recruitFactory;

//    protected InquiryFactory inquiryFactory;

    void setUp() {

        given(recruitIdGenerator.gen()).willReturn(RecruitId.of("1"));
        recruitFactory = new RecruitFactory(recruitIdGenerator);

//        given(inquiryGenerator.gen()).willReturn(InquiryItemId.of("1"));
//        inquiryFactory = new InquiryFactory(inquiryGenerator);
    }

    protected Recruit testRecruit() {
        return recruitFactory.create(meetupId);
    }

//    protected InquiryItem inquiryItem() {
//        return inquiryFactory.create("Test");
//    }
}
