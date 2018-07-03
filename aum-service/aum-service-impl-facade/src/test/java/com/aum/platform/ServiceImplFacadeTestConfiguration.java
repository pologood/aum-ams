package com.aum.platform;

import com.aum.platform.modules.fundaccount.FundAccountService;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.stereotype.Service;

/**
 * 启动入口
 *
 * @author xiayx
 */
@ComponentScan(
        basePackageClasses = ServiceImplFacadeTestConfiguration.class,
        useDefaultFilters = false,
        includeFilters = @ComponentScan.Filter(Service.class)
)
public class ServiceImplFacadeTestConfiguration {

    @Bean
    public ObjectMapper objectMapper() {
        return new ObjectMapper();
    }

    /** 使用平安作为第三方服务 */
    @Bean
    public FundAccountService thirdFundAccountService(FundAccountService pingAnFundAccountService) {
        return pingAnFundAccountService;
    }

}
