package com.aum.permission;

import com.alibaba.dubbo.common.utils.StringUtils;
import com.github.peacetrue.security.MessageDigestUtils;
import com.hjgj.permissions.model.User;
import net.unicon.cas.client.configuration.CasClientConfigurationProperties;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;

/**
 * 权限控制器
 *
 * @author xiayx
 */
@RequestMapping
public class PermissionController {

//    /**
//     * 首页
//     *
//     * @return
//     */
//    @RequestMapping(value = "/index.html", method = RequestMethod.GET)
//    public String index() {
//        return "index";
//    }
//
//    @RequestMapping(value = "/main.html", method = RequestMethod.GET)
//    public String main() {
//        return "main";
//    }

    @Autowired
    private CasClientConfigurationProperties casProperties;

    @ResponseBody
    @RequestMapping(value = "/navs.html", method = RequestMethod.GET)
    public String navs(HttpServletRequest request) {
        String navs = (String) request.getSession().getAttribute(SessionUtil.SessionSysteMenus);
        return navs;
    }

    @ResponseBody
    @RequestMapping(value = "/checkPassword", method = RequestMethod.POST)
    public Boolean checkPassword(HttpServletRequest request, String password) {
        User user = SessionUtil.getSessionUser(request);

        if (MessageDigestUtils.encode(MessageDigestUtils.getMD5(), password)
                .equals(user.getPassword())) {
            return true;
        }
        return false;
    }

    @RequestMapping("/logout")
    public String logout(HttpServletRequest request, String service) throws Exception {
        SessionUtil.clearSession(request);
        if (StringUtils.isBlank(service)) {
            service = casProperties.getServerLoginUrl();
        }
        //被拦截器拦截处理
        return "redirect:" + casProperties.getServerUrlPrefix() + "/logout?service=" + service;
    }


}
