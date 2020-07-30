package net.slipp.moim.domain.model.sample;

import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class SampleService {

    private final SampleMapper sampleMapper;

    public SampleService(SampleMapper sampleMapper) {
        this.sampleMapper = sampleMapper;
    }

    public List<Sample> findAll() {
        return sampleMapper.findAll();
    }
}
