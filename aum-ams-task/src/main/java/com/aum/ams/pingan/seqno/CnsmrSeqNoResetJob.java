package com.aum.ams.pingan.seqno;

import org.quartz.Job;
import org.quartz.JobExecutionContext;
import org.quartz.JobExecutionException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.support.atomic.RedisAtomicLong;

/**
 * 重置请求流水号
 *
 * @author xiayx
 */
public class CnsmrSeqNoResetJob implements Job {

    private Logger logger = LoggerFactory.getLogger(getClass());
    private RedisAtomicLong pingAnSeqNoAtomicLong;

    @Override
    public void execute(JobExecutionContext context) throws JobExecutionException {
        logger.info("重置平安请求流水号为0L");
        pingAnSeqNoAtomicLong.set(0L);
    }

    @Autowired
    public void setPingAnSeqNoAtomicLong(RedisAtomicLong pingAnSeqNoAtomicLong) {
        this.pingAnSeqNoAtomicLong = pingAnSeqNoAtomicLong;
    }
}
