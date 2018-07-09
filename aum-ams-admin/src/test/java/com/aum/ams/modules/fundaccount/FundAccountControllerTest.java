package com.aum.ams.modules.fundaccount;

import com.alibaba.boot.dubbo.autoconfigure.DubboAutoConfiguration;
import com.github.peacetrue.result.ResultBuilderAutoConfiguration;
import com.github.peacetrue.result.ResultWebAutoConfiguration;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.MessageSourceAutoConfiguration;
import org.springframework.boot.autoconfigure.data.web.SpringDataWebAutoConfiguration;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureWebMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;

import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

/**
 * tests for {@link FundAccountController}
 *
 * @author xiayx
 */
@RunWith(SpringRunner.class)
@SpringBootTest(
        classes = {
                DubboAutoConfiguration.class,
                FundAccountController.class,
                SpringDataWebAutoConfiguration.class,
                MessageSourceAutoConfiguration.class,
                ResultBuilderAutoConfiguration.class,
                ResultWebAutoConfiguration.class
        }
)
@AutoConfigureWebMvc
@AutoConfigureMockMvc
public class FundAccountControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @Test
    public void query() throws Exception {
        mockMvc.perform(MockMvcRequestBuilders.get("/FundAccount?name=刘&page=0&size=10"))
                .andExpect(status().isOk())
                .andDo(result -> System.out.println(result.getResponse().getContentAsString()));
    }

}