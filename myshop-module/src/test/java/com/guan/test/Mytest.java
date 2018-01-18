package com.guan.test;

import com.google.zxing.WriterException;
import com.guan.myshop.common.utils.UserAgentUtils;
import com.guan.myshop.common.utils.ZxingUtils;
import com.guan.myshop.module.user.entity.TbUser;
import com.guan.myshop.module.user.mapper.TbUserMapper;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import javax.servlet.http.HttpServletRequest;
import java.io.IOException;
import java.util.Date;
import java.util.List;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration({"classpath:spring-context.xml"})
public class Mytest {
    @Autowired
    private TbUserMapper tbUserMapper;
    @Test
    public void test(){
        TbUser tbUser = new TbUser();
        tbUser.setCreated(new Date());
        tbUser.setEmail("12345@qq.com");
        tbUser.setId(1L);
        tbUser.setPassword("admin");
        tbUser.setUsername("admin");
        tbUser.setPhone("158112223232");
        tbUser.setUpdated(new Date());
        tbUserMapper.insert(tbUser);
    }
    @Test
    public void  TestSelect(){

        List<TbUser> tbUserList = tbUserMapper.selectLike("adm");
        for (TbUser tbUser : tbUserList) {
            System.out.println(tbUser.toString());
        }
    }
}
