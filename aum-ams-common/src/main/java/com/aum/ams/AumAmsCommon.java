package com.aum.ams;

/**
 * 多个模块都涉及的公共信息
 *
 * @author xiayx
 */
public abstract class AumAmsCommon {

    /** redis key: 平安流水号中的序号 */
    public static final String
            REDIS_PINGAN_SEQNO = "aum.ams.pingan.CnsmrSeqNo";
}
