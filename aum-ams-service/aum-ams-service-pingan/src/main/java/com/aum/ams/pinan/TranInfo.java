package com.aum.ams.pinan;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;

@JsonIgnoreProperties(ignoreUnknown = true)
public class TranInfo {

    private String PayeeAcctNo;
    private String CorpId;
    private String ReturnCode;
    private String ReturnMsg;
    private String TranDate;
    private String CounterpartyBranchId;
    private String PayeeAcctName;
    private String CorpSeqNo;
    private String PayerAcctNo;
    private String CounterpartyAcctNo;
    private String CorpTranDate;
    private String PayerAcctName;
    private String TotalOccrAmt;
    private String OtherBranchId;
    private String BussTypeNo;
    private String CounterpartyAcctName;
    private String CorpTranTime;
    private String DealStatus;
    private String ActualCommision;
    private String REMARK;
    private String TranTime;

    public String getPayeeAcctNo() {
        return PayeeAcctNo;
    }

    @JsonProperty("PayeeAcctNo")
    public void setPayeeAcctNo(String payeeAcctNo) {
        PayeeAcctNo = payeeAcctNo;
    }

    public String getCorpId() {
        return CorpId;
    }

    public void setCorpId(String corpId) {
        CorpId = corpId;
    }

    public String getReturnCode() {
        return ReturnCode;
    }

    public void setReturnCode(String returnCode) {
        ReturnCode = returnCode;
    }

    public String getReturnMsg() {
        return ReturnMsg;
    }

    public void setReturnMsg(String returnMsg) {
        ReturnMsg = returnMsg;
    }

    public String getTranDate() {
        return TranDate;
    }

    public void setTranDate(String tranDate) {
        TranDate = tranDate;
    }

    public String getCounterpartyBranchId() {
        return CounterpartyBranchId;
    }

    public void setCounterpartyBranchId(String counterpartyBranchId) {
        CounterpartyBranchId = counterpartyBranchId;
    }

    public String getPayeeAcctName() {
        return PayeeAcctName;
    }

    public void setPayeeAcctName(String payeeAcctName) {
        PayeeAcctName = payeeAcctName;
    }

    public String getCorpSeqNo() {
        return CorpSeqNo;
    }

    public void setCorpSeqNo(String corpSeqNo) {
        CorpSeqNo = corpSeqNo;
    }

    public String getPayerAcctNo() {
        return PayerAcctNo;
    }

    public void setPayerAcctNo(String payerAcctNo) {
        PayerAcctNo = payerAcctNo;
    }

    public String getCounterpartyAcctNo() {
        return CounterpartyAcctNo;
    }

    public void setCounterpartyAcctNo(String counterpartyAcctNo) {
        CounterpartyAcctNo = counterpartyAcctNo;
    }

    public String getCorpTranDate() {
        return CorpTranDate;
    }

    public void setCorpTranDate(String corpTranDate) {
        CorpTranDate = corpTranDate;
    }

    public String getPayerAcctName() {
        return PayerAcctName;
    }

    public void setPayerAcctName(String payerAcctName) {
        PayerAcctName = payerAcctName;
    }

    public String getTotalOccrAmt() {
        return TotalOccrAmt;
    }

    public void setTotalOccrAmt(String totalOccrAmt) {
        TotalOccrAmt = totalOccrAmt;
    }

    public String getOtherBranchId() {
        return OtherBranchId;
    }

    public void setOtherBranchId(String otherBranchId) {
        OtherBranchId = otherBranchId;
    }

    public String getBussTypeNo() {
        return BussTypeNo;
    }

    public void setBussTypeNo(String bussTypeNo) {
        BussTypeNo = bussTypeNo;
    }

    public String getCounterpartyAcctName() {
        return CounterpartyAcctName;
    }

    public void setCounterpartyAcctName(String counterpartyAcctName) {
        CounterpartyAcctName = counterpartyAcctName;
    }

    public String getCorpTranTime() {
        return CorpTranTime;
    }

    public void setCorpTranTime(String corpTranTime) {
        CorpTranTime = corpTranTime;
    }

    public String getDealStatus() {
        return DealStatus;
    }

    public void setDealStatus(String dealStatus) {
        DealStatus = dealStatus;
    }

    public String getActualCommision() {
        return ActualCommision;
    }

    public void setActualCommision(String actualCommision) {
        ActualCommision = actualCommision;
    }

    public String getREMARK() {
        return REMARK;
    }

    public void setREMARK(String REMARK) {
        this.REMARK = REMARK;
    }

    public String getTranTime() {
        return TranTime;
    }

    public void setTranTime(String tranTime) {
        TranTime = tranTime;
    }
}
