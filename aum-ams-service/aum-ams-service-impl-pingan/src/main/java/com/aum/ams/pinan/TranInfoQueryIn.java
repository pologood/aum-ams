package com.aum.ams.pinan;

/**
 *
 * @author xiayx
 */
public class TranInfoQueryIn extends com.aum.ams.pinan.seqno.CnsmrSeqNo {

    private String BussTypeNo;
    private String CorpAgreementNo;
    private String RequestSeqNo;
    private String StartDate;
    private String EndDate;
    private String TranStatus;

    public String getBussTypeNo() {
        return BussTypeNo;
    }

    public void setBussTypeNo(String bussTypeNo) {
        BussTypeNo = bussTypeNo;
    }

    public String getCorpAgreementNo() {
        return CorpAgreementNo;
    }

    public void setCorpAgreementNo(String corpAgreementNo) {
        CorpAgreementNo = corpAgreementNo;
    }

    public String getRequestSeqNo() {
        return RequestSeqNo;
    }

    public void setRequestSeqNo(String requestSeqNo) {
        RequestSeqNo = requestSeqNo;
    }

    public String getStartDate() {
        return StartDate;
    }

    public void setStartDate(String startDate) {
        StartDate = startDate;
    }

    public String getEndDate() {
        return EndDate;
    }

    public void setEndDate(String endDate) {
        EndDate = endDate;
    }

    public String getTranStatus() {
        return TranStatus;
    }

    public void setTranStatus(String tranStatus) {
        TranStatus = tranStatus;
    }
}
