package net.slipp.moim.domain.model.sample;

import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface SampleMapper {

    List<Sample> findAll();
}
