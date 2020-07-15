package net.slipp.moim.application.service;

import liquibase.pro.packaged.id;
import net.slipp.moim.application.ApplicationTest;
import net.slipp.moim.domain.model.recruit.*;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.time.LocalDateTime;
import java.util.HashSet;
import java.util.Set;

class RecruitApplicationServiceTest extends ApplicationTest {

    @Test
    void changeRecruitTest() {

//        Recruit recruit = this.RecruitAggregate();
//
//        Assertions.assertNotNull(this.recruitRepository);
//        this.recruitRepository.save(recruit);
//
//        RecruitId id = recruit.id();
//        Content newContent = new Content("newContent");
//        Title newTitle = new Title("newTitle");
//        DeadLineDateTime aDeadLineDateTime = new DeadLineDateTime(LocalDateTime.now().plusWeeks(1L));
//
//        recruitApplicationService.changeRecruitDetail(
//                id.toString(),
//                newTitle,
//                newContent,
//                aDeadLineDateTime
//                );
//
//        Recruit newRecruit = this.recruitRepository.findById(recruit.id());
//
//        Assertions.assertNotNull(newRecruit);
//        Assertions.assertEquals(id.toString(), newRecruit.id().toString());
//        Assertions.assertEquals(newContent.text(), newRecruit.content().text());
//        Assertions.assertEquals(newTitle.text(), newRecruit.title().text());
//        Assertions.assertEquals(aDeadLineDateTime.dateTime(), newRecruit.deadLineDateTime().dateTime());
    }

    @Test
    void editChangeTest() {
//        Recruit recruit = this.RecruitAggregate();
//        this.recruitRepository.save(recruit);
//
//        RecruitId id = recruit.id();
//        InquiryDefinition a = new InquiryDefinition("1+2=?", true);
//        InquiryDefinition b = new InquiryDefinition("2+3=?", true);
//
//        Set<InquiryDefinition> anInquiryDefinitions = new HashSet<>();
//        anInquiryDefinitions.add(a);
//        anInquiryDefinitions.add(b);
//
//        recruitApplicationService.editInquiries(
//                id.toString(),
//                anInquiryDefinitions
//        );
//
//        Recruit newRecruit = this.recruitRepository.findById(recruit.id());
//
//        Assertions.assertNotNull(newRecruit);
//        Assertions.assertEquals(newRecruit.allInquiryDefinitions(), newRecruit.allInquiryDefinitions());
    }
}