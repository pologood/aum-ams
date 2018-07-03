package com.aum.platform.modules.fundaccount;

import java.util.List;

/**
 * 资金账号服务
 *
 * @author xiayx
 */
public interface FundAccountService {

    /**
     * 创建资金账（开户）
     *
     * @param fundAccountVo 资金账号
     */
    void createAccount(FundAccountVo fundAccountVo);

    /**
     * 查询资金账号
     *
     * @return 资金账户集合
     */
    List<FundAccountVo> query();
}
