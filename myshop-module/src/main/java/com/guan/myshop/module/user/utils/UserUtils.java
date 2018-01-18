package com.guan.myshop.module.user.utils;

import com.guan.myshop.module.sys.dto.LoginDTO;
import com.guan.myshop.module.user.entity.TbUser;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.subject.Subject;

import javax.servlet.http.HttpSession;

/**
 * @author guan
 * @version V1.0
 */
public class UserUtils {
    public static TbUser getsession(HttpSession session){

        return (TbUser) session.getAttribute("user");
    }

    public static LoginDTO getPrincipal(){
        Subject subject = SecurityUtils.getSubject();
        LoginDTO loginDTO = (LoginDTO) subject.getPrincipal();
        if (loginDTO != null) {
            return loginDTO;
        }
        return null;
    }
}
