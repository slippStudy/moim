package net.slipp.moim.support.config.db;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.context.annotation.Configuration;

@Configuration
@MapperScan(basePackages = {MybatisConfig.BASE_PACKAGE})
public class MybatisConfig {
    public static final String BASE_PACKAGE = "net.slipp.moim.domain";
    public static final String CONFIG_LOCATION_PATH = "classpath:mybatis/mybatis-config.xml";
    public static final String SQL_LOCATION_PATH = "classpath:/mapper/**/*.xml";
}
