package net.slipp.moim.application.service;

import net.slipp.moim.application.Application;
import net.slipp.moim.domain.model.recruit.*;
import net.slipp.utils.Assertions;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.Set;

@Service
public class RecruitApplicationService {

    private final RecruitRepository recruitRepository;

    public RecruitApplicationService(RecruitRepository recruitRepository) {
        this.recruitRepository = recruitRepository;
    }


    @Application
    public Recruit createRecruit() {
        Recruit recruit = Recruit.Recruit(this.recruitRepository.nextId());
        this.recruitRepository.save(recruit);
        return recruit;
    }

    public void changeRecruitDetail(
            String aRecruitId,
            String aTitle,
            Content aContent,
            DeadLineDateTime aDeadLineDateTime
    ) {
        Assertions.assertArgumentNotEmpty(aTitle, "제목이 존재해야 합니다.");
        Assertions.assertArgumentNotEmpty(aContent.text(), "내용이 존재해야 합니다.");
        Assertions.assertArgumentTrue(aDeadLineDateTime.dateTime().isAfter(LocalDateTime.now()), "마감일시는 현재보다 이후여야 합니다.");

        Recruit recruit = this.recruitRepository.findById(RecruitId.of(aRecruitId));

        recruit.setTitle(aTitle);
        recruit.setContent(aContent);
        recruit.setDeadLineDateTime(aDeadLineDateTime);
        this.recruitRepository.save(recruit);
    }

    public void editInquiries(
            String aRecruitId,
            Set<InquiryDefinition> anInquiryDefinitions) {
        Assertions.assertArgumentNotNull(aRecruitId, "RecruitId가 존재해야 합니다.");
        Assertions.assertArgumentNotNull(anInquiryDefinitions, "질의문이 존재해야 합니다.");


        Recruit recruit = this.recruitRepository.findById(RecruitId.of(aRecruitId));
        recruit.changeInquiries(anInquiryDefinitions);

        this.recruitRepository.save(recruit);
    }

    public ManagedUrl getManagedUrl(
            String aRecruitId
    ) {
        Assertions.assertArgumentNotNull(aRecruitId, "RecruitId가 존재해야 합니다.");
        ManagedUrl managedUrl = this.recruitRepository.findById(RecruitId.of(aRecruitId)).managedUrl();

        return managedUrl;
    }

}
