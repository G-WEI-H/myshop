package com.guan.myshop.module.sys.security;

import com.google.code.kaptcha.Constants;
import com.guan.myshop.common.utils.StringUtils;
import com.guan.myshop.module.sys.dto.LoginDTO;
import com.guan.myshop.module.user.entity.TbUser;
import com.guan.myshop.module.user.service.TbUserService;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.AuthenticationException;
import org.apache.shiro.authc.AuthenticationInfo;
import org.apache.shiro.authc.AuthenticationToken;
import org.apache.shiro.authc.SimpleAuthenticationInfo;
import org.apache.shiro.authz.AuthorizationInfo;
import org.apache.shiro.authz.SimpleAuthorizationInfo;
import org.apache.shiro.realm.AuthorizingRealm;
import org.apache.shiro.session.Session;
import org.apache.shiro.subject.PrincipalCollection;
import org.apache.shiro.subject.Subject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * @author guan
 * @version V1.0
 * @Title: ${file_name}
 * @Package ${package_name}
 * @date ${date} ${time}
 */
@Service
public class SystemAuthorizingRealm extends AuthorizingRealm {
    private static final String ROLE_ADMIN="admin";
    private static final String ROLE_ADMIN_PERMISSIONS="sys:user:list,sys:user:save";
    @Autowired
   private TbUserService tbUserService;
    /**
     * 授权查询回调函数, 进行鉴权但缓存中无用户的授权信息时调用
     * @param principalCollection
     * @return
     */
    @Override
    protected AuthorizationInfo doGetAuthorizationInfo(PrincipalCollection principalCollection) {
        SimpleAuthorizationInfo info = new SimpleAuthorizationInfo();
        info.addRole(ROLE_ADMIN);
        for (String permission : StringUtils.split(ROLE_ADMIN_PERMISSIONS, ",")) {
            info.addStringPermission(permission);
        }
        return info;
    }

    /**
     * 认证回调函数, 登录时调用
     * @param authenticationToken
     * @return
     * @throws AuthenticationException
     */
    @Override
    protected AuthenticationInfo doGetAuthenticationInfo(AuthenticationToken authenticationToken) throws AuthenticationException {
        UsernamePasswordToken usernamePasswordToken = (UsernamePasswordToken) authenticationToken;
        String validateCode = (String) getSession().getAttribute(com.google.code.kaptcha.Constants.KAPTCHA_SESSION_KEY);
        if (usernamePasswordToken.getValidateCode()==null ||!validateCode.toUpperCase().equals(usernamePasswordToken.getValidateCode().toUpperCase())) {
                throw new AuthenticationException("msg:验证码输入有误,请重新输入");
        }

        TbUser  tbUser = tbUserService.getByLoginId(usernamePasswordToken.getUsername());
        if (tbUser != null) {
            String loginId =usernamePasswordToken.getUsername();
            String loginPwd = new String(usernamePasswordToken.getPassword());
            String isRememberMe=usernamePasswordToken.isRememberMe()?"on":"";
            return new SimpleAuthenticationInfo(new LoginDTO(loginId,loginPwd,isRememberMe,null),tbUser.getPassword(),getName());
        }
        //认证失败
        return null;
    }


    /**
     * 获取 Shiro 管理的 Session
     *
     * @return
     */
    public Session getSession(){
        Subject subject = SecurityUtils.getSubject();
        Session session = subject.getSession(false);
        if (session == null) {
             session = subject.getSession();
        }
        return session;
    }
}
