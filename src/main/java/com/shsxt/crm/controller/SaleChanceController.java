package com.shsxt.crm.controller;

import com.shsxt.crm.base.BaseController;
import com.shsxt.crm.model.ResultInfo;
import com.shsxt.crm.po.SaleChance;
import com.shsxt.crm.query.SaleChanceQuery;
import com.shsxt.crm.service.SaleChanceService;
import com.shsxt.crm.utils.LoginUserUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;
import java.util.Map;

/**
 * @author 康晓伟
 * @auther: 康晓伟
 * @date: 2018/10/15 14:27
 * @description: shsxt_crm
 */

@Controller
@RequestMapping("saleChance")
public class SaleChanceController extends BaseController {
    @Autowired
    private SaleChanceService saleChanceService;

    /**
     * 加载页面
     * @return
     */
    @RequestMapping("index")
    public String index(){
        return "sale_chance";
    }

    /**
     * 分页操作
     * @param page
     * @param rows
     * @param saleChanceQuery
     * @return
     */
    @RequestMapping("querySaleChancesByParams")
    @ResponseBody
    public Map<String, Object> querySaleChancesByParams(@RequestParam(defaultValue = "1")Integer page,
                                                        @RequestParam(defaultValue = "10")Integer rows,
                                                        SaleChanceQuery saleChanceQuery){
        saleChanceQuery.setPageNum(page);
        saleChanceQuery.setPageSize(rows);
        return saleChanceService.queryForPage(saleChanceQuery);
    }

    /**
     * 添加或更新
     * @param saleChance
     * @param request
     * @return
     */
    @RequestMapping("saveOrUpdateSaleChance")
    @ResponseBody
    public ResultInfo saveOrUpdateSaleChance(SaleChance saleChance, HttpServletRequest request){
        Integer id = LoginUserUtil.releaseUserIdFromCookie(request);
        saleChanceService.saveOrUpdateSaleChance(saleChance,id);
        return success(200,"操作成功");
    }

    /**
     * 删除操作
     * @param ids
     * @return
     */
    @RequestMapping("deleteSaleChance")
    @ResponseBody
    public ResultInfo deleteSaleChance(Integer[] ids){
        saleChanceService.deleteBatch(ids);
        return success("操作成功");
    }

}
