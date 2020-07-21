package net.slipp.moim.domain.model.sample;

import net.slipp.moim.infra.jdbc.PersistenceConfig;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.data.jdbc.DataJdbcTest;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.context.annotation.Import;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.ContextConfiguration;

import java.util.List;
import java.util.Optional;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.*;

@ActiveProfiles("test")
@DataJdbcTest
@ContextConfiguration(classes = {PersistenceConfig.class})
@AutoConfigureTestDatabase(replace = AutoConfigureTestDatabase.Replace.NONE)
class SampleRepositoryTest {

    @Autowired
    private SampleRepository sampleRepository;

    @Test
    void findTest() {
        String name = "sample1";
        Iterable<Sample> sampleList = sampleRepository.findAll();
        assertThat(sampleList).isNotEmpty();

        Optional<Sample> sample = sampleRepository.findByName(name);
        assertThat(sample.get().getName()).isEqualTo(name);
    }
}
