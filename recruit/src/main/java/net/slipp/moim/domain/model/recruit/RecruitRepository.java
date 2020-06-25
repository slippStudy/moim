package net.slipp.moim.domain.model.recruit;

import org.springframework.stereotype.Repository;

@Repository
public interface RecruitRepository {

    RecruitId nextId();

    void save(Recruit recruit);

    Recruit findById(RecruitId of);
}
