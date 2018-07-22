package com.aum.ams.pingan;

import com.aum.ams.Common;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.redis.connection.RedisConnectionFactory;
import org.springframework.data.redis.support.atomic.RedisAtomicLong;

/**
 * 平安配置
 *
 * @author xiayx
 */
@Configuration
public class PingAnConfiguration {

    /** 用于获取请求流水号中的序号 */
    @Bean
    public RedisAtomicLong pingAnSeqNoAtomicLong(RedisConnectionFactory factory) {
        return new RedisAtomicLong(Common.REDIS_PINGAN_SEQNO, factory);
    }

}
