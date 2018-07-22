package com.aum.ams.pinan.seqno;

/**
 * 流水号
 *
 * @author xiayx
 */
public class CnsmrSeqNo implements CnsmrSeqNoAware {

    private String CnsmrSeqNo;

    public String getCnsmrSeqNo() {
        return CnsmrSeqNo;
    }

    public void setCnsmrSeqNo(String cnsmrSeqNo) {
        CnsmrSeqNo = cnsmrSeqNo;
    }
}
