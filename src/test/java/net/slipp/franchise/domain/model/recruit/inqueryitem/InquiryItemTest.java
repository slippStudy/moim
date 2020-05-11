package net.slipp.franchise.domain.model.recruit.inqueryitem;

import net.slipp.franchise.domain.model.recruit.Recruit;
import net.slipp.franchise.domain.model.recruit.RecruitCommonTestSupport;
import org.apache.commons.lang3.RandomUtils;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.BDDMockito.given;

@ExtendWith(MockitoExtension.class)
class InquiryItemTest {

    InquiryItem dut;

    @Mock
    protected InquiryGenerator inquiryGenerator;

    protected InquiryFactory inquiryFactory;

    @BeforeEach
    void setup(){
        given(inquiryGenerator.gen()).willReturn(InquiryItemId.of("1"));
        inquiryFactory = new InquiryFactory(inquiryGenerator);

    }

    @Test
    @DisplayName("질의를 생성")
    void create() {
        String expected = "where are you live?";
        dut = inquiryFactory.create(expected);

        Assertions.assertEquals(expected, dut.getQuestion());
    }

    @Test
    @DisplayName("질의를 수정")
    void update() {
        String expected = "where are you live?";
        String changedExpected = "who are you?";
        dut = inquiryFactory.create(expected);
        Assertions.assertEquals(expected, dut.getQuestion());

        dut.changeQuestion(changedExpected);
        Assertions.assertEquals(changedExpected, dut.getQuestion());
    }
}