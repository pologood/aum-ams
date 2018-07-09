package com.aum.tutorial.dubbo;

import org.junit.Assert;
import org.junit.Test;
import org.springframework.context.support.ClassPathXmlApplicationContext;

/**
 * @author xiayx
 */
public class DubboTest {

    @Test
    public void basic() throws Exception {
        //Can't assign requested address
        //-Djava.net.preferIPv4Stack=true
        ClassPathXmlApplicationContext provider = new ClassPathXmlApplicationContext(new String[]{"com/aum/tutorial/dubbo/provider.xml"});
        provider.start();
        DemoService providerDemoService = provider.getBean(DemoService.class);
        Assert.assertTrue(providerDemoService != null);

        ClassPathXmlApplicationContext consumer = new ClassPathXmlApplicationContext(new String[]{"com/aum/tutorial/dubbo/consumer.xml"});
        consumer.start();
        DemoService consumerDemoService = consumer.getBean(DemoService.class);
        Assert.assertTrue(consumerDemoService != null);

        Assert.assertNotEquals(providerDemoService, consumerDemoService);

//        System.in.read();
    }
}
