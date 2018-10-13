package com.shsxt.crm.controller;

import com.shsxt.crm.base.BaseController;
import com.shsxt.crm.service.UserService;
import com.shsxt.crm.utils.LoginUserUtil;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
/**
 * @author 康晓伟
 * @date: 2018/10/12 16:25
 * @description: shsxt_crm
 */
@Controller
public class MainController extends BaseController {
    @Resource
    private UserService userService;

    @RequestMapping("main")
    public String index(HttpServletRequest request){
        int id = LoginUserUtil.releaseUserIdFromCookie(request);
        request.setAttribute("user",userService.queryById(id));
        return "main";
    }
}
