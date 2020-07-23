package net.slipp.moim.domain.model.sample;

import net.slipp.moim.support.config.db.DatasourceConfig;
import net.slipp.moim.support.config.db.MybatisConfig;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.ContextConfiguration;

import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;

@ActiveProfiles("test")
@ContextConfiguration(classes = {MybatisConfig.class, DatasourceConfig.class})
@SpringBootTest
class SampleMapperTest {

    @Autowired
    private SampleMapper sampleMapper;

    @Test
    void name() {
        List<Sample> result = sampleMapper.findAll();

        assertThat(result).isNotEmpty();
    }
}
