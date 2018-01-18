package com.guan.myshop.module.user.web;

import com.guan.myshop.common.utils.UserAgentUtils;
import com.guan.myshop.common.validator.BeanValidators;
import com.guan.myshop.common.web.BaseController;
import com.guan.myshop.module.user.entity.TbUser;
import com.guan.myshop.module.user.service.TbUserService;
import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import javax.servlet.http.HttpServletRequest;
import java.util.List;

/**
 * @author guan
 * @version V1.0
 */

@Controller
@RequestMapping(value = "user")
public class UserController extends BaseController{
    @Autowired
    private TbUserService tbUserService;

    @ModelAttribute
    public TbUser getById(Long id){
        TbUser entity=null;
        if (id!=null) {
            entity=tbUserService.getById(id);
        }

        else{
            entity=new TbUser();
        }
        return entity;
    }

    @RequestMapping(value = "form",method = RequestMethod.GET)
    public String addUser(){

        return  "modules/user/userForm";
    }
    @RequiresPermissions("sys:user:list")
    @RequestMapping(value = "list",method=RequestMethod.GET)
    public String userlist(Model model,HttpServletRequest request){
        List<TbUser> list = tbUserService.selectAll();
        model.addAttribute("list",list);
        return "modules/user/userlist";
    }
    @RequiresPermissions("sys:user:save")
    @RequestMapping(value = "save",method = RequestMethod.POST)
    public  String addUser(TbUser tbUser, Model model){
        // 服务器端数据验证
        if (!beanValidator(model, tbUser)) {
            return addUser();
        }
         tbUserService.save(tbUser);
        return "redirect:/user/list";
    }
    @RequiresPermissions("sys:user:delete")
    @RequestMapping(value = "delete",method = RequestMethod.GET)
    public String deleteUser(Long id,Model model,HttpServletRequest request){
        tbUserService.deleteUser(id);
        return userlist(model,request);
    }

    @RequestMapping(value = "search",method = RequestMethod.POST)
    public  String selectSearch(HttpServletRequest request,Model model){
        String table_search = request.getParameter("table_search");
        System.out.println(table_search);
        if (table_search != null) {
            List<TbUser> userList = tbUserService.selectLike(table_search);
            model.addAttribute("userlist",userList);
        }
        return "modules/user/userlist";
    }
}
