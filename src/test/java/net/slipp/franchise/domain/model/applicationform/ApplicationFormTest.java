package net.slipp.franchise.domain.model.applicationform;

import net.slipp.franchise.domain.model.recruit.Recruit;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.assertj.core.util.Sets.newHashSet;

class ApplicationFormTest extends ApplicationFormCommonTestSupport {

    ApplicationForm dut;
    Recruit recruit;

    @BeforeEach
    void setUp() {
        super.setUp();
        recruit = getRecruit();
    }

    @Test
    @DisplayName("질의가 없는 신청서를 생성합니다.")
    void create() {
        dut = new ApplicationForm(recruit);

        Assertions.assertEquals(dut.getInquiryItems(), newHashSet());
    }
}