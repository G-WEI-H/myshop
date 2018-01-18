package com.guan.myshop.module.sys.web;


import com.guan.myshop.common.utils.IDUtils;
import com.guan.myshop.module.sys.dto.LoginDTO;
import com.guan.myshop.module.user.utils.UserUtils;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import javax.servlet.http.HttpServletRequest;


/**
 * @author guan
 * @version V1.0
 */

@Controller
public class LoginController {
    @RequestMapping(value = {"","login"},method = RequestMethod.GET)
    public String login(){
        LoginDTO loginDTO = UserUtils.getPrincipal();
        if (loginDTO != null) {
            return "redirect:/main";
        }
        return "modules/sys/login";
    }

    @RequestMapping(value = "login",method = RequestMethod.POST)
    public  String loginFail(HttpServletRequest request){
        // 验证失败清空验证码
        request.getSession().setAttribute(com.google.code.kaptcha.Constants.KAPTCHA_SESSION_KEY, IDUtils.genId());

        return "modules/sys/login";
    }

}



