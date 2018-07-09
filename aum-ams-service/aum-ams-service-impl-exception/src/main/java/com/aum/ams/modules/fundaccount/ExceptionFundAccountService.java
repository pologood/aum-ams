package com.aum.ams.modules.fundaccount;

import com.aum.ams.result.ResultException;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

/**
 * 账户服务的异常测试实现
 *
 * @author xiayx
 */
@Service
public class ExceptionFundAccountService implements FundAccountService {

    @Override
    public Long add(FundAccountVo fundAccountVo) {
        throw new ResultException("500", "服务端错误");
    }

    @Override
    public Page<FundAccountVo> query(FundAccountQueryParam queryParam, Pageable pageable) {
        throw new ResultException("500", "服务端错误");
    }


}
