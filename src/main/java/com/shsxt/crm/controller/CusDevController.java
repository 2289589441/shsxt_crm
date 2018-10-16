package com.shsxt.crm.controller;

import com.shsxt.crm.base.BaseController;
import com.shsxt.crm.model.ResultInfo;
import com.shsxt.crm.po.CusDevPlan;
import com.shsxt.crm.po.SaleChance;
import com.shsxt.crm.query.CusDevPlanQuery;
import com.shsxt.crm.query.SaleChanceQuery;
import com.shsxt.crm.service.CusDevPlanService;
import com.shsxt.crm.service.SaleChanceService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.annotation.Resource;
import java.util.Map;

/**
 * @author 康晓伟
 * @auther: 康晓伟
 * @date: 2018/10/16 14:02
 * @description: shsxt_crm
 */
@Controller
@RequestMapping("cusDevPlan")
public class CusDevController extends BaseController {
    @Resource
    private SaleChanceService saleChanceService;
    @Resource
    private CusDevPlanService cusDevPlanService;

    /**
     * 加载页面
     * @param sid
     * @param model
     * @return
     */
    @RequestMapping("index")
    public String index(Integer sid , Model model){
        SaleChance saleChance = saleChanceService.queryById(sid);
        model.addAttribute(saleChance);
        return "cus_dev_plan_detail";
    }

    @RequestMapping("queryCusDevPlansByParams")
    @ResponseBody
    public Map<String, Object> queryCusDevPlansByParams(@RequestParam(defaultValue = "1")Integer page,
                                                        @RequestParam(defaultValue = "10")Integer rows,
                                                        CusDevPlanQuery cusDevPlanQuery){
        cusDevPlanQuery.setPageNum(page);
        cusDevPlanQuery.setPageSize(rows);
        return cusDevPlanService.queryForPage(cusDevPlanQuery);
    }

    /**
     * 添加 或 更新
     * @param cusDevPlan
     * @param sid
     * @return
     */
    @RequestMapping("saveOrUpdateCusDevPlans")
    @ResponseBody
    public ResultInfo saveOrUpdateCusDevPlans(CusDevPlan cusDevPlan, Integer sid){
        cusDevPlanService.saveOrUpdateCusDevPlans(cusDevPlan, sid);
        return success("操作成功");
    }
    /**
     * 删除操作
     * @param ids
     * @return
     */
    @RequestMapping("deleteCusDevPlan")
    @ResponseBody
    public ResultInfo deleteCusDevPlan(Integer[] ids){
        cusDevPlanService.deleteBatch(ids);
        return success("操作成功");
    }
}
