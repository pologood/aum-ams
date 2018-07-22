package com.aum.ams.pinan.seqno;

/**
 * @author xiayx
 */
public class CnsmrSeqNoImpl extends CnsmrSeqNo {

    private String ClientNo;
    private String OpenAcctDate;

    public String getClientNo() {
        return ClientNo;
    }

    public void setClientNo(String clientNo) {
        ClientNo = clientNo;
    }

    public String getOpenAcctDate() {
        return OpenAcctDate;
    }

    public void setOpenAcctDate(String openAcctDate) {
        OpenAcctDate = openAcctDate;
    }
}
