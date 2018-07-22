package com.aum.ams.modules.fundaccount;

import com.aum.ams.pinan.TranInfo;
import com.aum.ams.pinan.TranInfoQueryIn;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.domain.PageRequest;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.List;

/**
 * tests for {@link PingAnFundAccountService}
 *
 * @author xiayx
 */
@RunWith(SpringRunner.class)
@SpringBootTest
public class PingAnFundAccountServiceTest {

    @Autowired
    private PingAnFundAccountService pingAnFundAccountService;

    @Test
    public void TranInfoQuery() throws Exception {
        TranInfoQueryIn in = new TranInfoQueryIn();
        in.setBussTypeNo("100160");
        in.setCorpAgreementNo("Q000400269");
        in.setRequestSeqNo("H222852018051712345678");
        in.setStartDate("20180517");
        in.setEndDate("20180517");
        in.setTranStatus("0");
        List<TranInfo> tranInfos = pingAnFundAccountService.TranInfoQuery(in);
        System.out.println(tranInfos);
    }

    @Test
    public void createAccount() throws Exception {
        pingAnFundAccountService.add(new FundAccountDTO());
    }

    @Test
    public void query() throws Exception {
        pingAnFundAccountService.query(null, new PageRequest(0, 10));
    }

}