package com.aum.ams.modules.fundaccount;

import org.dozer.Mapping;

/**
 * 平安资金账户
 *
 * @author xiayx
 * @see FundAccountDTO
 */
public class PingAnFundAccount {

    /*账户*/
    @Mapping("code")
    private String Code;
    /** 密码 */
    @Mapping("password")
    private String PasswordMd5;
    @Mapping("type")
    private String Type;
    /** 类型 */
    @Mapping("accountType")
    private String AcctType;

    /*人/客户*/
    /** 证件类型，枚举 */
    @Mapping("certificateType")
    private String GlobalType;
    /** 证件号码 */
    @Mapping("certificateCode")
    private String GlobalId;
    /** 姓名 */
    @Mapping("name")
    private String ClientName;
    /** 性别 */
    @Mapping("sex")
    private String Sex;
    /** 地区代码 */
    @Mapping("regionCode")
    private String AreaCode;
    /** 地址 */
    @Mapping("address")
    private String Address;
    /** 手机号 */
    @Mapping("mobile")
    private String Mobile;
    /** 邮箱 */
    @Mapping("email")
    private String Email;
    /** 备注 */
    @Mapping("remark")
    private String Remark;

    /*银行卡*/
    /** 银行卡编号 */
    @Mapping("bankCardCode")
    private String AcctNo;

    public String getCode() {
        return Code;
    }

    public void setCode(String code) {
        Code = code;
    }

    public String getPasswordMd5() {
        return PasswordMd5;
    }

    public void setPasswordMd5(String passwordMd5) {
        PasswordMd5 = passwordMd5;
    }

    public String getType() {
        return Type;
    }

    public void setType(String type) {
        Type = type;
    }

    public String getAcctType() {
        return AcctType;
    }

    public void setAcctType(String acctType) {
        AcctType = acctType;
    }

    public String getGlobalType() {
        return GlobalType;
    }

    public void setGlobalType(String globalType) {
        GlobalType = globalType;
    }

    public String getGlobalId() {
        return GlobalId;
    }

    public void setGlobalId(String globalId) {
        GlobalId = globalId;
    }

    public String getClientName() {
        return ClientName;
    }

    public void setClientName(String clientName) {
        ClientName = clientName;
    }

    public String getSex() {
        return Sex;
    }

    public void setSex(String sex) {
        Sex = sex;
    }

    public String getAreaCode() {
        return AreaCode;
    }

    public void setAreaCode(String areaCode) {
        AreaCode = areaCode;
    }

    public String getAddress() {
        return Address;
    }

    public void setAddress(String address) {
        Address = address;
    }

    public String getMobile() {
        return Mobile;
    }

    public void setMobile(String mobile) {
        Mobile = mobile;
    }

    public String getEmail() {
        return Email;
    }

    public void setEmail(String email) {
        Email = email;
    }

    public String getRemark() {
        return Remark;
    }

    public void setRemark(String remark) {
        Remark = remark;
    }

    public String getAcctNo() {
        return AcctNo;
    }

    public void setAcctNo(String acctNo) {
        AcctNo = acctNo;
    }
}
