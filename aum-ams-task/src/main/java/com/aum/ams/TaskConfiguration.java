package com.aum.ams;

import com.aum.ams.pingan.seqno.CnsmrSeqNoResetJob;
import org.quartz.Trigger;
import org.quartz.spi.JobFactory;
import org.springframework.beans.factory.config.AutowireCapableBeanFactory;
import org.springframework.boot.autoconfigure.quartz.AutowireCapableBeanJobFactory;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.scheduling.quartz.CronTriggerFactoryBean;
import org.springframework.scheduling.quartz.JobDetailFactoryBean;
import org.springframework.scheduling.quartz.SchedulerFactoryBean;

import java.util.List;

/**
 * 任务配置
 *
 * @author xiayx
 */
@Configuration
public class TaskConfiguration {

    @Bean
    public JobFactory jobFactory(AutowireCapableBeanFactory beanFactory) {
        return new AutowireCapableBeanJobFactory(beanFactory);
    }

    @Bean
    public SchedulerFactoryBean schedulerFactoryBean(JobFactory jobFactory, List<Trigger> triggers) {
        SchedulerFactoryBean schedulerFactoryBean = new SchedulerFactoryBean();
        schedulerFactoryBean.setJobFactory(jobFactory);
        schedulerFactoryBean.setTriggers(triggers.toArray(new Trigger[triggers.size()]));
        return schedulerFactoryBean;
    }

    /*---------------------平安-----------------------*/
    @Bean
    public JobDetailFactoryBean pingAnCnsmrSeqNoResetJob() {
        JobDetailFactoryBean factoryBean = new JobDetailFactoryBean();
        factoryBean.setGroup("aum.ams");
        factoryBean.setName("PingAnCnsmrSeqNoReset");
        factoryBean.setDescription("重置序号");
        factoryBean.setJobClass(CnsmrSeqNoResetJob.class);
        factoryBean.setDurability(false);
        return factoryBean;
    }

    @Bean
    public CronTriggerFactoryBean pingAnCnsmrSeqNoResetTrigger() {
        CronTriggerFactoryBean factoryBean = new CronTriggerFactoryBean();
        factoryBean.setGroup("aum.ams");
        factoryBean.setName("PingAnCnsmrSeqNoReset");
        factoryBean.setJobDetail(pingAnCnsmrSeqNoResetJob().getObject());
        factoryBean.setCronExpression("0 0 0 * * ?");
        factoryBean.setDescription("每天凌晨(00:00:00)设置序号为0");
        return factoryBean;
    }
}
