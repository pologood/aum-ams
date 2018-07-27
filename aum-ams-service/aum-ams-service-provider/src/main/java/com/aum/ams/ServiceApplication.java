package com.aum.ams;

import com.aum.mybatis.tk.TkMybatisConfiguration;
import com.aum.spring.EnvironmentUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.context.annotation.Import;
import org.springframework.context.annotation.ImportResource;

import static org.springframework.boot.context.config.ConfigFileApplicationListener.CONFIG_LOCATION_PROPERTY;

/**
 * 服务提供者
 *
 * @author xiayx
 */
@SpringBootApplication
@Import({TkMybatisConfiguration.class})
@ImportResource("classpath:/dubbo.xml")
public class ServiceApplication {

    private static Logger logger = LoggerFactory.getLogger(ServiceApplication.class);

    public static void main(String[] args) {
        EnvironmentUtils.setConventionalAdditionalConfigLocation("aum-ams-service-provider");
//        EnvironmentUtils.setAdditionalConfigLocation("classpath:template/");// 用于外部文件测试
        logger.info("{}: {}", CONFIG_LOCATION_PROPERTY, EnvironmentUtils.getConfigLocation());
        ConfigurableApplicationContext context = SpringApplication.run(ServiceApplication.class, args);
//        Environment bean = context.getBean(Environment.class);
//        String property = bean.getProperty("info.app.java.source");
//        System.out.println(property);
    }


}
