package com.guan.myshop.module.user.service.impl;

import com.guan.myshop.common.utils.IDUtils;
import com.guan.myshop.module.sys.dto.LoginDTO;
import com.guan.myshop.module.user.entity.TbUser;
import com.guan.myshop.module.user.mapper.TbUserMapper;
import com.guan.myshop.module.user.service.TbUserService;
import org.apache.commons.lang3.StringUtils;
import org.apache.ibatis.session.RowBounds;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.DigestUtils;
import tk.mybatis.mapper.entity.Example;

import java.util.Date;
import java.util.List;

@Service
public class TbUserServiceImpl implements TbUserService {
    @Autowired
    private TbUserMapper tbUserMapper;


    // 使用用户名和密码登录
    @Override
    public TbUser login(LoginDTO loginDTO) {
        TbUser result=null;
      // 封装查询条件
        Example example= new Example(TbUser.class);
        example.createCriteria()
                .andEqualTo("email",loginDTO.getLoginId())
                .orEqualTo("phone",loginDTO.getLoginId())
                .orEqualTo("username",loginDTO.getLoginId());
        //根据条件查询
        List<TbUser> tbUsers = tbUserMapper.selectByExample(example);
       // 判断是否存在
        if (tbUsers != null && tbUsers.size()==1) {
                result=tbUsers.get(0);
                String password =DigestUtils.md5DigestAsHex(loginDTO.getLoginPwd().getBytes()) ;
            if (password.equals(result.getPassword())) {
                return result;
            }
        }
        // 登录失败
        else{

        }
        return null;
    }

    @Override
    public TbUser getById(Long id) {

        return tbUserMapper.selectByPrimaryKey(id);
    }

    @Override
    public void save(TbUser tbUser) {
        if (tbUser.getId() != null) {
            // 密码字段不为并且长度为 0 时，则将密码字段设为 null
            if (StringUtils.isBlank(tbUser.getPassword())) {
                tbUser.setPassword(null);
            }

            // 密码加密
            else {
                tbUser.setPassword(DigestUtils.md5DigestAsHex(tbUser.getPassword().getBytes()));
            }
            tbUserMapper.updateByPrimaryKeySelective(tbUser);
        }
        else{
            tbUser.setId(IDUtils.genId());
            // 新增用户时密码需要加密
            tbUser.setPassword(DigestUtils.md5DigestAsHex(tbUser.getPassword().getBytes()));
            tbUser.setCreated(new Date());
            tbUser.setUpdated(new Date());
            tbUserMapper.insert(tbUser);
        }
    }

    @Override
    public List<TbUser> selectAll() {
        List<TbUser> list = tbUserMapper.selectAll();

        return list;
    }

    @Override
    public void deleteUser(Long id) {

        tbUserMapper.deleteByPrimaryKey(id);
    }

    @Override
    public boolean check(TbUser tbUser) {
        return false;
    }

    @Override
    public List<TbUser> selectLike(String likeName) {
        List<TbUser> tbUserList = tbUserMapper.selectLike(likeName);
        return tbUserList;
    }

    @Override
    public TbUser getByLoginId(String LoginId) {
        TbUser tbUser = null;
        // 封装查询条件
        Example example = new Example(TbUser.class);
        example.createCriteria()
                .andEqualTo("email", LoginId)
                .orEqualTo("phone", LoginId)
                .orEqualTo("username", LoginId);
        //根据条件查询
        List<TbUser> tbUsers = tbUserMapper.selectByExample(example);
        // 判断是否存在
        if (tbUsers != null && tbUsers.size() == 1) {
            tbUser = tbUsers.get(0);
        }
        return tbUser;
    }
}
