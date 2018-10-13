package com.shsxt.crm.controller;
import com.shsxt.crm.base.BaseController;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.servlet.http.HttpServletRequest;
/**
 * @author 康晓伟
 * @date: 2018/10/12 16:25
 * @description: shsxt_crm
 */
@Controller
public class IndexController extends BaseController {
    @RequestMapping("index")
    public String index(){
        return "index";
    }
}
