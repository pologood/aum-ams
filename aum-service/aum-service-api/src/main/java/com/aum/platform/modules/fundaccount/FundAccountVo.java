package com.aum.platform.modules.fundaccount;

import java.io.Serializable;

/**
 * 资金账号
 *
 * @author xiayx
 */
public class FundAccountVo implements Serializable {

    private Long id;
    private String name;
    private String password;

    @Override
    public String toString() {
        return "FundAccountVo{" +
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
