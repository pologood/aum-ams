package com.aum.ams;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.BeansException;
import org.springframework.beans.factory.config.BeanFactoryPostProcessor;
import org.springframework.beans.factory.config.ConfigurableListableBeanFactory;

import java.util.Objects;

/**
 * 服务类 别名 注册
 *
 * @author xiayx
 */
public class ServiceAliasProcessor implements BeanFactoryPostProcessor {

    private Logger logger = LoggerFactory.getLogger(getClass());
    private String source, target;

    public ServiceAliasProcessor(String source, String target) {
        this.source = Objects.requireNonNull(source);
        this.target = Objects.requireNonNull(target);
    }

    @Override
    public void postProcessBeanFactory(ConfigurableListableBeanFactory beanFactory) throws BeansException {
        beanFactory.getBeanNamesIterator().forEachRemaining(beanName -> {
            if (beanName.startsWith(source) && beanName.endsWith("Service")) {
                String alias = beanName.replace(source, target);
                logger.debug("register alias: '{}' -> '{}'", beanName, alias);
                beanFactory.registerAlias(beanName, alias);
            }
        });
    }
}
