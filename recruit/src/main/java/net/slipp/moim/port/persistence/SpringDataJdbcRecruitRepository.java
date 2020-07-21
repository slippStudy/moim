package net.slipp.moim.port.persistence;

import net.slipp.moim.domain.model.recruit.Recruit;
import net.slipp.moim.domain.model.recruit.RecruitId;
import net.slipp.moim.domain.model.recruit.RecruitRepository;

public class SpringDataJdbcRecruitRepository implements RecruitRepository {
    @Override
    public RecruitId nextId() {
        return null;
    }

    @Override
    public void save(Recruit recruit) {

    }

    @Override
    public Recruit findById(RecruitId of) {
        return null;
    }
}
