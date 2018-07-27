package com.aum.ams.modules.fundaccount;

import com.alibaba.dubbo.config.annotation.Reference;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

/**
 * 资金账户控制器
 *
 * @author xiayx
 */
@Controller
@RequestMapping("/FundAccount")
public class FundAccountController {

    private FundAccountService fundAccountService;

    @GetMapping("/list")
    public String list() {
        return "FundAccount/list";
    }

    @GetMapping({"/detail", "/view", "/add", "/modify"})
    public String detail() {
        return "FundAccount/detail";
    }

    @ResponseBody
    @PostMapping
    public Object add(FundAccountDTO fundAccountVo) {
        return fundAccountService.add(fundAccountVo);
    }

    @ResponseBody
    @GetMapping
    public Page<FundAccountDTO> query(FundAccountQueryParam queryParam, Pageable pageable) {
        return fundAccountService.query(queryParam, pageable);
    }

    @ResponseBody
    @GetMapping("/{id}")
    public FundAccountDTO get(@PathVariable Long id) {
        return fundAccountService.get(id);
    }

    @ResponseBody
    @PutMapping
    public int modify(FundAccountDTO fundAccountVo) {
        return fundAccountService.modify(fundAccountVo);
    }

    @ResponseBody
    @DeleteMapping("/{id}")
    public int delete(@PathVariable Long id) {
        return fundAccountService.delete(id);
    }

    @ResponseBody
    @DeleteMapping
    public int delete(@RequestParam("id") Long[] id) {
        return fundAccountService.delete(id);
    }


    @Reference(group = "local")
    public void setFundAccountService(FundAccountService fundAccountService) {
        this.fundAccountService = fundAccountService;
    }
}
