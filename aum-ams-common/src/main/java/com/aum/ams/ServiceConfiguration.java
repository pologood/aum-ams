package com.aum.ams;

import com.aum.dozer.ListDozerBeanMapper;
import com.aum.dozer.ListMapper;
import org.dozer.Mapper;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.autoconfigure.condition.ConditionalOnClass;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.Arrays;

/**
 * 服务层配置
 *
 * @author xiayx
 */
@Configuration
@ConditionalOnClass(Mapper.class)
public class ServiceConfiguration {

    @Value("${aum.ams.dozer.mappingFiles:dozerBeanMapping.xml}")
    private String[] mappingFiles;

    @Bean
    public ListMapper mapper() {
        return new ListDozerBeanMapper(Arrays.asList(mappingFiles));
    }

}