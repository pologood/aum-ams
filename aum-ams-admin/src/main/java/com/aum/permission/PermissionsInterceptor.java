package com.aum.permission;

import com.alibaba.dubbo.config.annotation.Reference;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.hjgj.permissions.api.IPermissionsApi;
import com.hjgj.permissions.model.Resource;
import com.hjgj.permissions.model.User;
import net.unicon.cas.client.configuration.CasClientConfigurationProperties;
import org.jasig.cas.client.util.AssertionHolder;
import org.jasig.cas.client.validation.Assertion;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.util.StringUtils;
import org.springframework.web.servlet.handler.HandlerInterceptorAdapter;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.*;
import java.util.stream.Collectors;


/**
 * 拦截指定path，进行权限验证，及用户的本地session过期后，重新进行赋值
 */
public class PermissionsInterceptor extends HandlerInterceptorAdapter {

    @Autowired
    private CasClientConfigurationProperties casProperties;
    @Reference(url = "dubbo://192.168.10.189:30000", version = "1.0")
    private IPermissionsApi permissionsApi;
    @Autowired
    private ObjectMapper objectMapper;
    @Value("${aum.resource.moduleFlag}")
    private String moduleFlag;

    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
        Assertion assertion = AssertionHolder.getAssertion();
        //实际cas-client-core中org.jasig.cas.client.authentication.AuthenticationFilter已经进行了单点登录认证，这里主要是为了获得用户信息
        if (SessionUtil.getSessionUser(request) == null && assertion == null) {
            //没有登录，跳转到没有登录页面
            response.sendRedirect(casProperties.getServerUrlPrefix());
            return false;
        }
        User user = SessionUtil.getSessionUser(request);
        if (user == null) {
            //存储Session:用户登录名
            user = permissionsApi.getUserByUsername(assertion.getPrincipal().getName());
            request.getSession().setAttribute(SessionUtil.SessionSystemLoginUserName, user);
        }

        //初始化菜单
        if (StringUtils.isEmpty(SessionUtil.getSessionMenus(request))) {
            List<Resource> resourceList = permissionsApi.queryListResource(user.getId(), moduleFlag);
            String menuList = treeMenuList(resourceList);
            List<Map<String, Object>> menus = objectMapper.readValue(menuList, new TypeReference<List<Map<String, Object>>>() {});
            request.getSession().setAttribute("menus", menus);
            request.getSession().setAttribute(SessionUtil.SessionSysteMenus, menuList);
        }
        //判断权限，没有权限，进入没有权限页面
        setCurrentMenu(request);
        return true;

    }

    private void setCurrentMenu(HttpServletRequest request) {
        @SuppressWarnings("unchecked")
        List<Map<String, Object>> menus = (List<Map<String, Object>>) request.getSession().getAttribute("menus");
        if (menus == null) return;
        String servletPath = request.getServletPath();
        List<Map<String, Object>> target = new ArrayList<>(2);
        request.setAttribute("openNames", "['产品管理']");
        request.setAttribute("activeName", "产品列表");
        request.setAttribute("titles", Arrays.asList("产品管理", "产品列表"));
        if (findByHref(menus, servletPath, target)) {
            List<String> titles = target.stream().map(map -> (String) map.get("title")).collect(Collectors.toList());
            Collections.reverse(target);
            request.setAttribute("openNames", "['" + titles.get(0) + "']");
            request.setAttribute("activeName", titles.get(1));
            request.setAttribute("titles", titles);
        }
    }

    @SuppressWarnings("unchecked")
    private boolean findByHref(List<Map<String, Object>> source, String href, List<Map<String, Object>> target) {
        for (Map<String, Object> menu : source) {
            List<Map<String, Object>> children = (List<Map<String, Object>>) menu.get("children");
            if (children != null) {
                if (findByHref(children, href, target)) {
                    target.add(menu);
                    return true;
                }
            } else if (href.equals(menu.get("href"))) {
                target.add(menu);
                return true;
            }
        }
        return false;
    }

    //菜单树形结构
    public String treeMenuList(List<Resource> resourceList) throws JsonProcessingException {
        List<NavsModel> navsList = new ArrayList<>();
        for (Resource res : resourceList) {
            if (res.getParentId() == 0) {//父级菜单开始添加
                NavsModel parent = new NavsModel();
                parent.setHref(res.getUrl());
                parent.setIcon("icon-computer");
                parent.setTitle(res.getName());
                if (ifChilds(resourceList, res.getId())) {//存在子集
                    List<Resource> childs = new ArrayList<Resource>();
                    childs = getChildList(resourceList, res.getId(), childs);
                    List<NavsModel> childsList = new ArrayList<NavsModel>();
                    for (Resource chil : childs) {
                        NavsModel child = new NavsModel();
                        child.setHref(chil.getUrl());
                        child.setIcon("icon-computer");
                        child.setTitle(chil.getName());
                        childsList.add(child);
                    }
                    parent.setChildren(childsList);
                }
                navsList.add(parent);
            }
        }
        return objectMapper.writeValueAsString(navsList);
    }


    //获取父id下的子集合  
    private static List<Resource> getChildList(List<Resource> list, int pId, List<Resource> reList) {
        for (Resource testEntity : list) {
            if (testEntity.getParentId() == pId) {//查询下级菜单
                reList.add(testEntity);
                if (ifChilds(list, testEntity.getId())) {
                    getChildList(list, testEntity.getId(), reList);
                }
            }
        }
        return reList;
    }

    //判断是否存在子集  
    private static boolean ifChilds(List<Resource> list, int pId) {
        boolean flag = false;
        for (Resource testEntity : list) {
            if (testEntity.getParentId() == pId) {
                flag = true;
                break;
            }
        }
        return flag;
    }

}
