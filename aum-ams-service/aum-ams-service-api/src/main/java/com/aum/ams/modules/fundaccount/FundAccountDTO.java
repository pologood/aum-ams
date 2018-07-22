package com.aum.ams.modules.fundaccount;

import java.io.Serializable;

/**
 * 资金账户
 *
 * @author xiayx
 */
public class FundAccountDTO implements Serializable {

    /*账户*/
    /** 主键 */
    private String id;
    /** 密码 */
    private String password;
    private String type;
    /** 类型 */
    private String accountType;
    /** 签约网点 */
    private String signSubBranchId;

    /*人/客户*/
    /** 证件类型，枚举 */
    private String certificateType;
    /** 证件号码 */
    private String certificateCode;
    /** 姓名 */
    private String name;
    /** 性别 */
    private String sex;
    /** 地区代码 */
    private String regionCode;
    /** 地址 */
    private String address;
    /** 手机号 */
    private String mobile;
    /** 邮箱 */
    private String email;
    /** 备注 */
    private String remark;

    /*银行卡*/
    /** 银行卡编号 */
    private String bankCardCode;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getAccountType() {
        return accountType;
    }

    public void setAccountType(String accountType) {
        this.accountType = accountType;
    }

    public String getCertificateType() {
        return certificateType;
    }

    public void setCertificateType(String certificateType) {
        this.certificateType = certificateType;
    }

    public String getCertificateCode() {
        return certificateCode;
    }

    public void setCertificateCode(String certificateCode) {
        this.certificateCode = certificateCode;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getSex() {
        return sex;
    }

    public void setSex(String sex) {
        this.sex = sex;
    }

    public String getRegionCode() {
        return regionCode;
    }

    public void setRegionCode(String regionCode) {
        this.regionCode = regionCode;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getMobile() {
        return mobile;
    }

    public void setMobile(String mobile) {
        this.mobile = mobile;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getRemark() {
        return remark;
    }

    public void setRemark(String remark) {
        this.remark = remark;
    }

    public String getSignSubBranchId() {
        return signSubBranchId;
    }

    public void setSignSubBranchId(String signSubBranchId) {
        this.signSubBranchId = signSubBranchId;
    }

    public String getBankCardCode() {
        return bankCardCode;
    }

    public void setBankCardCode(String bankCardCode) {
        this.bankCardCode = bankCardCode;
    }
}
