package com.aum.ams;

import com.aum.ams.pinan.jackson.DataResultMixIn;
import com.aum.ams.pinan.seqno.CnsmrSeqNoAspect;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.PropertyNamingStrategy;
import com.github.peacetrue.result.DataResult;
import org.dozer.DozerBeanMapper;
import org.dozer.Mapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.EnableAspectJAutoProxy;

import java.io.IOException;

/**
 * 平安配置
 *
 * @author xiayx
 */
@Configuration
@EnableAspectJAutoProxy(proxyTargetClass = true)
public class ServicePingAnConfiguration {

    @Autowired
    public void initConfig(@Value("${aum.ams.pingan.config:conf/config.properties}") String config) throws IOException {
        ServicePingAnUtils.initConfig(config);
    }

    @Bean
    public Mapper mapper() {
        return new DozerBeanMapper();
    }

    @Bean
    public ObjectMapper pingAnObjectMapper() {
        ObjectMapper mapper = new ObjectMapper();
        mapper.setPropertyNamingStrategy(PropertyNamingStrategy.UPPER_CAMEL_CASE);
        mapper.addMixIn(DataResult.class, DataResultMixIn.class);
        return mapper;
    }


    @Bean
    public CnsmrSeqNoAspect cnsmrSeqNoAspect() {
        return new CnsmrSeqNoAspect();
    }

}
