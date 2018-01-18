package com.guan.myshop.module.sys.web;

import com.guan.myshop.common.utils.UserAgentUtils;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.HandlerInterceptor;

import javax.servlet.http.HttpServletRequest;

/**
 * @author guan
 * @version V1.0
 */
@Controller
public class MainController  {
    @RequestMapping(value = "main",method = RequestMethod.GET)
    public  String main(HttpServletRequest request){
        UserAgentUtils.getUserAgent(request);

        return "modules/sys/index";
    }
}
