package net.slipp.moim.domain.model.recruit;


import net.slipp.utils.Assertions;
import org.springframework.stereotype.Service;

@Service
public class RecruitURLCreator {

    private final RecruitRepository recruitRepository;

    public RecruitURLCreator(RecruitRepository recruitRepository) {
        this.recruitRepository = recruitRepository;
    }


    public ManagedUrl createManagedURL(
            RecruitId aRecruitId
    ) {
        Assertions.assertArgumentNotNull(aRecruitId, "id가 존재해야 합니다.");
        //TODO URL 생성 기능

        return new ManagedUrl(aRecruitId, "NEED URL GENERATOR");
    }
}
