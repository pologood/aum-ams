package com.aum.ams.modules.fundaccount;

import com.aum.mybatis.pagehelper.PageHelperUtils;
import com.aum.vo.VoConvertUtils;
import com.github.pagehelper.PageRowBounds;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.StringUtils;
import tk.mybatis.mapper.entity.Example;

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
    public Long add(FundAccountVo fundAccountVo) {
        logger.info("在本地创建资金账户: {}", fundAccountVo);
        FundAccount fundAccount = VoConvertUtils.convert(fundAccountVo, FundAccount.class);
        fundAccountMapper.insert(fundAccount);
        fundAccountVo.setId(fundAccount.getId());
        logger.debug("账户主键为：{}", fundAccount.getId());
        return fundAccount.getId();
    }

    @Override
    @Transactional(readOnly = true)
    public Page<FundAccountVo> query(FundAccountQueryParam queryParam, Pageable pageable) {
        logger.info("从本地查询资金账户");
        logger.debug("queryParam: {}, page: {}", queryParam, pageable);

        Example example = new Example(FundAccount.class);
        if (queryParam != null) {
            Example.Criteria criteria = example.createCriteria();
            if (StringUtils.hasText(queryParam.getName())) {
                criteria.andLike("name", "%" + queryParam.getName().trim() + "%");
            }
        }
        PageHelperUtils.orderBy(example, pageable);

        PageRowBounds pageRowBounds = PageHelperUtils.mapToRowBounds(pageable);
        List<FundAccountVo> fundAccountVos = VoConvertUtils.convert(
                fundAccountMapper.selectByExampleAndRowBounds(example, pageRowBounds),
                FundAccountVo.class);
        return new PageImpl<>(fundAccountVos, pageable, pageRowBounds.getTotal());
    }

    @Override
    public FundAccountVo get(Long id) {
        logger.info("从本地获取主键为'{}'的资金账户", id);
        FundAccount fundAccount = fundAccountMapper.selectByPrimaryKey(id);
        return VoConvertUtils.convert(fundAccount, FundAccountVo.class);
    }

    @Override
    public int modify(FundAccountVo fundAccountVo) {
        logger.info("从本地修改主键为'{}'的资金账户", fundAccountVo.getId());
        FundAccount fundAccount = VoConvertUtils.convert(fundAccountVo, FundAccount.class);
        return fundAccountMapper.updateByPrimaryKeySelective(fundAccount);
    }

    @Override
    public int delete(Long id) {
        logger.info("从本地删除主键为'{}'的资金账户", id);
        return fundAccountMapper.deleteByPrimaryKey(id);
    }
}
