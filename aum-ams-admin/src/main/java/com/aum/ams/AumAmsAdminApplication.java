package com.aum.ams;

import com.aum.permission.PermissionConfiguration;
import net.unicon.cas.client.configuration.EnableCasClient;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Import;
import org.springframework.web.servlet.config.annotation.ViewControllerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurerAdapter;

import java.util.HashMap;
import java.util.Map;

/**
 * 启动入口
 *
 * @author xiayx
 */
@SpringBootApplication
@EnableCasClient
@Import(PermissionConfiguration.class)
public class AumAmsAdminApplication {

    public static void main(String[] args) {
        SpringApplication.run(AumAmsAdminApplication.class, args);
    }

    @Bean
    public WebMvcConfigurer addViewControllers() {
        return new WebMvcConfigurerAdapter() {
            @Override
            public void addViewControllers(ViewControllerRegistry registry) {
                Map<String, String> viewNames = new HashMap<>();
                viewNames.put("/FundAccount/list", "/FundAccount/list");
                viewNames.put("/FundAccount/detail", "/FundAccount/detail");
                viewNames.put("/FundAccount/add", "/FundAccount/detail");
                viewNames.put("/FundAccount/view", "/FundAccount/detail");
                viewNames.put("/FundAccount/modify", "/FundAccount/detail");
                viewNames.forEach((key, value) -> registry.addViewController(key + ".html").setViewName(value));
            }
        };
    }
}
