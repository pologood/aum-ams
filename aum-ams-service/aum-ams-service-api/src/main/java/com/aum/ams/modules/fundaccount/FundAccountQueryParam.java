package com.aum.ams.modules.fundaccount;

import java.io.Serializable;

/**
 * 资金账号查询参数
 *
 * @author xiayx
 */
public class FundAccountQueryParam implements Serializable {

    private Long id;
    private String name;
    private String password;

    @Override
    public String toString() {
        return "FundAccountQueryParam{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", password='" + password + '\'' +
                '}';
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }
}
