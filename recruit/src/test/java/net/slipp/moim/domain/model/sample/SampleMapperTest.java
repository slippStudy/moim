package net.slipp.moim.domain.model.sample;

import net.slipp.moim.support.config.db.DatasourceConfig;
import net.slipp.moim.support.config.db.MybatisConfig;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.ContextConfiguration;

import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;

@ActiveProfiles("test")
@ContextConfiguration(classes = {MybatisConfig.class, DatasourceConfig.class})
@AutoConfigureTestDatabase(replace = AutoConfigureTestDatabase.Replace.NONE)
@SpringBootTest(classes = SampleMapper.class)
class SampleMapperTest {

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
