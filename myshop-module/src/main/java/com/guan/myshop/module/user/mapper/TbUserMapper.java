package com.guan.myshop.module.user.mapper;


import com.guan.myshop.module.user.entity.TbUser;
import tk.mybatis.mapper.MyMapper;

import java.util.List;

public interface TbUserMapper extends MyMapper<TbUser> {
    public List<TbUser> selectLike(String LikeName);
}