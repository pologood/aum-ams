package com.aum.platform.modules.fundaccount;

import com.aum.vo.VoConvertUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

/**
 * 资金账户服务的本地实现
 *
 * @author xiayx
 */
@Service
@Transactional
public class LocalFundAccountService implements FundAccountService {

    private Logger logger = LoggerFactory.getLogger(getClass());

    @Autowired
    private FundAccountMapper fundAccountMapper;

    @Override
    public void createAccount(FundAccountVo fundAccountVo) {
        logger.info("在本地创建资金账户: {}", fundAccountVo);
        fundAccountMapper.insert(VoConvertUtils.convert(fundAccountVo, FundAccount.class));
    }

    @Override
    @Transactional(readOnly = true)
    public List<FundAccountVo> query() {
        logger.info("从本地查询资金账户");
        return VoConvertUtils.convert(fundAccountMapper.selectAll(), FundAccountVo.class);
    }

}
