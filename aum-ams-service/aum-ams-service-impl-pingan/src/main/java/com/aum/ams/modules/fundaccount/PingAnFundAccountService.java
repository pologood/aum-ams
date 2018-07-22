package com.aum.ams.modules.fundaccount;

import com.aum.ams.AumAmsServicePingAnUtils;
import com.aum.ams.pinan.seqno.CnsmrSeqNoImpl;
import com.aum.ams.pinan.PingAnPage;
import com.aum.ams.pinan.TranInfo;
import com.aum.ams.pinan.TranInfoQueryIn;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.github.peacetrue.result.DataResult;
import com.github.peacetrue.result.DataResultImpl;
import com.pabank.sdk.PABankSDK;
import org.dozer.Mapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;

import static com.aum.ams.AumAmsServiceApiUtils.uncheckedInvoke;

/**
 * 资金账户服务的平安实现
 *
 * @author xiayx
 */
@Service
public class PingAnFundAccountService implements FundAccountService {

    private static Logger logger = LoggerFactory.getLogger(PingAnFundAccountService.class);

    @Autowired
    private Mapper mapper;
    @Autowired
    private ObjectMapper pingAnObjectMapper;

    @Override
    public Object add(FundAccountDTO fundAccountVo) {
        logger.info("在平安银行创建资金账户");
        PingAnFundAccount fundAccount = mapper.map(fundAccountVo, PingAnFundAccount.class);
        String body = uncheckedInvoke(() -> pingAnObjectMapper.writeValueAsString(fundAccount));
        Map<String, Object> mapResult = uncheckedInvoke(() -> PABankSDK.getInstance().apiInter(body, "ClientSign"));
        DataResult<CnsmrSeqNoImpl> dataResult = pingAnObjectMapper.convertValue(mapResult, new TypeReference<DataResultImpl<CnsmrSeqNoImpl>>() {});
        AumAmsServicePingAnUtils.checkResult(dataResult);
        return dataResult.getData().getClientNo();
    }

    public List<TranInfo> TranInfoQuery(TranInfoQueryIn in) {
        // b.交易测试，传入参数服务ID，JSON报文流水号字段需传入22位
        String req = uncheckedInvoke(() -> pingAnObjectMapper.writeValueAsString(in));
        logger.info("in: {}", req);
        Map<String, Object> mapResult = uncheckedInvoke(() -> PABankSDK.getInstance().apiInter(req, "TranInfoQuery"));
        logger.debug("out: {}", mapResult);
        mapResult.put("Content", mapResult.get("TranInfoArray"));
        TypeReference<DataResultImpl<PingAnPage<List<TranInfo>>>> typeReference = new TypeReference<DataResultImpl<PingAnPage<List<TranInfo>>>>() {};
        DataResult<PingAnPage<List<TranInfo>>> dataResult = pingAnObjectMapper.convertValue(mapResult, typeReference);
        AumAmsServicePingAnUtils.checkResult(dataResult);
        return dataResult.getData().getContent();
    }

    @Override
    public Page<FundAccountDTO> query(FundAccountQueryParam queryParam, Pageable pageable) {
        //TODO 平安银行未提供查询接口
        return null;
    }

}
