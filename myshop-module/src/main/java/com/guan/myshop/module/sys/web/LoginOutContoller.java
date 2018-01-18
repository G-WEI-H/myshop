package com.guan.myshop.module.sys.web;


import com.guan.myshop.module.sys.dto.LoginDTO;
import com.guan.myshop.module.user.utils.UserUtils;
import org.apache.shiro.SecurityUtils;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;

/**
 * @author guan
 * @version V1.0
 */
@Controller
public class LoginOutContoller {
    @RequestMapping(value = "loginout")
    public String logout(HttpServletRequest request) {
        LoginDTO principal = UserUtils.getPrincipal();
        if (principal != null) {
            // 交给 Shiro 操作
            SecurityUtils.getSubject().logout();
        }
        return "redirect:/login";
    }
}
