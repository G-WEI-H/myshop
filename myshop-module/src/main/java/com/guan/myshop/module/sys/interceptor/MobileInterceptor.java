package com.guan.myshop.module.sys.interceptor;


import com.guan.myshop.common.utils.UserAgentUtils;
import org.apache.commons.lang3.StringUtils;
import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * @author guan
 * @version V1.0
 * @Title: ${file_name}
 * @Package ${package_name}
 * @date ${date} ${time}
 */
public class MobileInterceptor implements HandlerInterceptor {

    @Override
    public boolean preHandle(HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse, Object o) throws Exception {
        return true;
    }

    @Override
    public void postHandle(HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse, Object o, ModelAndView modelAndView) throws Exception {
        if (modelAndView != null) {
            if (UserAgentUtils.isMobileOrTablet(httpServletRequest)&& !StringUtils.startsWithIgnoreCase(modelAndView.getViewName(),"redirect")) {
                 modelAndView.setViewName("mobile"+modelAndView.getViewName().replace("modules",""));
            }
        }
    }

    @Override
    public void afterCompletion(HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse, Object o, Exception e) throws Exception {

    }
}
