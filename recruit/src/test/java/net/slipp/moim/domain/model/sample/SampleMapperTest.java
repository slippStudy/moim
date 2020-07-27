package net.slipp.moim.domain.model.sample;

import net.slipp.support.BaseRepositoryTest;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;

class SampleMapperTest extends BaseRepositoryTest {

    @Autowired
    private SampleMapper sampleMapper;

    @Test
    void name() {
        List<Sample> result = sampleMapper.findAll();

        assertThat(result.get(0).getName()).isEqualTo("sample1");
        assertThat(result.get(0).getAddress()).isEqualTo("테스트 지역");
        assertThat(result.get(1).getName()).isEqualTo("sample2");
        assertThat(result.get(1).getAddress()).isEqualTo("테스트 지역");
    }
}
