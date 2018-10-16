package com.shsxt.crm.controller;

import com.shsxt.crm.base.BaseController;
import com.shsxt.crm.model.ResultInfo;
import com.shsxt.crm.model.UserInfo;
import com.shsxt.crm.service.UserService;
import com.shsxt.crm.utils.LoginUserUtil;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import java.util.List;
import java.util.Map;

/**
 * @author 康晓伟
 * @auther: 康晓伟
 * @date: 2018/10/13 11:56
 * @description: shsxt_crm
 */
@Controller
@RequestMapping("user")
public class UserController extends BaseController {
    @Resource
    private UserService userService;
    /**
     * 登录操作
     * @param userName 用户名
     * @param userPwd 用户密码
     * @return resultInfo
     */
    @RequestMapping("login")
    @ResponseBody
    public ResultInfo login(String userName,String userPwd){
            UserInfo userInfo = userService.login(userName, userPwd);
            return success("登录成功",userInfo);
    }
    @RequestMapping("upDateUserPwd")
    @ResponseBody
    public ResultInfo upDateUserPwd(String userName, String oldUserPwd, String newUserPwd, String conformUserPwd, HttpServletRequest request){

        //获取cookie中的id
        int id = LoginUserUtil.releaseUserIdFromCookie(request);
        //更新密码
        userService.upDateUserPwd(userName,oldUserPwd,newUserPwd,conformUserPwd,id);
        return success("更新成功");
    }

    @RequestMapping("queryCustomerManagers")
    @ResponseBody
    public List<Map<String, Object>> queryCustomerManagers(){
        return userService.queryCustomerManagers();
    }
}

