package com.aum.ams.modules.fundaccount;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import javax.annotation.Nullable;

/**
 * 资金账户服务
 *
 * @author xiayx
 */
public interface FundAccountService {

    /**
     * 创建资金账户（开户）
     *
     * @param fundAccountVo 资金账号
     * @return 不确定返回类型，暂定Object
     */
    default Object add(FundAccountDTO fundAccountVo) {
        throw new UnsupportedOperationException();
    }


    /**
     * 分页查询资金账户
     *
     * @param queryParam 查询参数
     * @param pageable   分页参数
     * @return 资金账户集合
     */
    default Page<FundAccountDTO> query(@Nullable FundAccountQueryParam queryParam, Pageable pageable) {
        throw new UnsupportedOperationException();
    }


    /**
     * 获取资金账户
     *
     * @param id 主键
     * @return 资金账户
     */
    default FundAccountDTO get(Long id) {
        throw new UnsupportedOperationException();
    }

    /**
     * 修改资金账户
     *
     * @param fundAccountVo 资金账户
     * @return 受影响的行数
     */
    default int modify(FundAccountDTO fundAccountVo) {
        throw new UnsupportedOperationException();
    }

    /**
     * 删除资金账户
     *
     * @param id 主键
     * @return 受影响的行数
     */
    default int delete(Long... id) {
        throw new UnsupportedOperationException();
    }
}
