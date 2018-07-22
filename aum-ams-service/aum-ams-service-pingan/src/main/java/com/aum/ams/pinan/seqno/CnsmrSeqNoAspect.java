package com.aum.ams.pinan.seqno;

import com.github.peacetrue.util.DateTimeFormatterUtils;
import org.apache.commons.lang3.StringUtils;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.data.redis.support.atomic.RedisAtomicLong;

import java.time.LocalDate;

/**
 * 流水号切面
 *
 * @author xiayx
 */
@Aspect
public class CnsmrSeqNoAspect {

    private Logger logger = LoggerFactory.getLogger(getClass());

    private RedisAtomicLong redisAtomicLong;
    @Value("${aum.ams.pingan.uid:H22285}")
    private String uid;

    @Before("execution(public * com.aum.ams.modules..*(..))&&args(cnsmrSeqNoAware))")
    public void handleCnsmrSeqNo(CnsmrSeqNoAware cnsmrSeqNoAware) throws Throwable {
        long index = redisAtomicLong.incrementAndGet();
        String strIndex = String.valueOf(index);
        if (strIndex.length() > 8) throw new IllegalStateException("序号超过最大长度");
        String seqNo = StringUtils.leftPad(strIndex, 8, '0');
        String cnsmrSeqNo = uid + DateTimeFormatterUtils.SHORT_DATE.format(LocalDate.now()) + seqNo;
        cnsmrSeqNoAware.setCnsmrSeqNo(cnsmrSeqNo);
        logger.info("设置请求流水号:{}", cnsmrSeqNo);
    }

    @Autowired
    public void setRedisAtomicLong(RedisAtomicLong redisAtomicLong) {
        this.redisAtomicLong = redisAtomicLong;
    }
}
