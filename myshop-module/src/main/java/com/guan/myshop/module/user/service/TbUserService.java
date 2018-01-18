package com.guan.myshop.module.user.service;


import com.guan.myshop.module.sys.dto.LoginDTO;
import com.guan.myshop.module.user.entity.TbUser;

import java.util.List;

public interface TbUserService {
    // 用户登录
    public TbUser login(LoginDTO loginDTO);
    public TbUser getById(Long id);
    public void save(TbUser tbUser);
    public List<TbUser> selectAll();
    public void deleteUser(Long id);
    public boolean check(TbUser tbUser);
    public  List<TbUser> selectLike(String LikeName);
    public TbUser getByLoginId(String LoginId);
}
