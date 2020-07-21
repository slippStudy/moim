package net.slipp.moim.domain.model.sample;

import org.springframework.context.ApplicationListener;
import org.springframework.context.annotation.Bean;
import org.springframework.core.convert.converter.Converter;
import org.springframework.core.io.ClassPathResource;
import org.springframework.data.jdbc.core.convert.JdbcCustomConversions;
import org.springframework.data.jdbc.repository.config.AbstractJdbcConfiguration;
import org.springframework.data.relational.core.mapping.event.BeforeSaveEvent;
import org.springframework.jdbc.core.JdbcOperations;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.jdbc.datasource.init.DataSourceInitializer;
import org.springframework.jdbc.datasource.init.ResourceDatabasePopulator;
import org.springframework.lang.Nullable;

import javax.sql.DataSource;

import java.sql.Clob;
import java.sql.SQLException;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.concurrent.atomic.AtomicLong;

import static java.util.Arrays.asList;

public class SampleJdbcConfig extends AbstractJdbcConfiguration {

    final AtomicLong id = new AtomicLong(0);

    @Bean
    public ApplicationListener<BeforeSaveEvent> idGenerator() {

        return event -> {
            if (event.getEntity() instanceof Sample) {
                setIds((Sample) event.getEntity());
            }
        };
    }

    private void setIds(Sample sample) {

        if (sample.getId() == null || sample.getId() == 0) {
            sample.setId(id.incrementAndGet());
        }
//        if (sample.getId() == null || sample.getId().getId() == 0) {
//            sample.setId(SampleId.of(id.incrementAndGet()));
//        }
//
//        Manual manual = legoSet.getManual();
//
//        if (manual != null) {
//            manual.setId((long) legoSet.getId());
//        }
    }

    @Bean
    public NamedParameterJdbcTemplate namedParameterJdbcTemplate(JdbcOperations operations) {
        return new NamedParameterJdbcTemplate(operations);
    }

}
