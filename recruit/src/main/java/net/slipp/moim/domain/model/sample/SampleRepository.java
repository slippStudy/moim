package net.slipp.moim.domain.model.sample;

import org.springframework.data.jdbc.repository.query.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface SampleRepository extends CrudRepository<Sample, Long> {

    /*
    Spring Data Jdbc는 JPA처럼 자동 생성 쿼리는 지원되지 않고 명시적 쿼리만 지원한다.
     */
    @Query("select * from Sample s where s.name = :name")
    Optional<Sample> findByName(String name);
}
