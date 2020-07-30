package net.slipp.support;

import net.slipp.moim.support.config.db.DatasourceConfig;
import net.slipp.moim.support.config.db.MybatisConfig;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.transaction.annotation.Transactional;

@ActiveProfiles("test")
@ContextConfiguration(classes = {MybatisConfig.class, DatasourceConfig.class})
@AutoConfigureTestDatabase(replace = AutoConfigureTestDatabase.Replace.NONE)
@SpringBootTest
@Transactional
public class BaseRepositoryTest {
}
