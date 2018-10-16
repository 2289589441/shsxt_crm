package com.shsxt.crm.service;

import com.shsxt.crm.base.BaseService;
import com.shsxt.crm.dao.CusDevPlanMapper;
import com.shsxt.crm.dao.SaleChanceMapper;
import com.shsxt.crm.po.CusDevPlan;
import com.shsxt.crm.po.SaleChance;
import com.shsxt.crm.utils.AssertUtil;
import org.apache.commons.lang3.StringUtils;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.Date;

/**
 * @author 康晓伟
 * @auther: 康晓伟
 * @date: 2018/10/16 14:57
 * @description: shsxt_crm
 */
@Service
public class CusDevPlanService extends BaseService<CusDevPlan> {
    @Resource
    private CusDevPlanMapper cusDevPlanMapper;
    @Resource
    private SaleChanceMapper saleChanceMapper;

    public void saveOrUpdateCusDevPlans(CusDevPlan cusDevPlan,Integer sid){
        /*
        * 1 判断参数
        * */
        checkCusDevPlans(cusDevPlan);

        /*
        * 2 补全参数
        * */
        cusDevPlan.setUpdateDate(new Date());

        if (cusDevPlan.getId() == null) {
            cusDevPlan.setIsValid(1);
            cusDevPlan.setCreateDate(new Date());
            cusDevPlan.setSaleChanceId(sid);
            AssertUtil.isTrue(cusDevPlanMapper.save(cusDevPlan)<1,"添加失败");
            /*
            * 判断当前开发状态
            * */
            SaleChance saleChance = saleChanceMapper.queryById(sid);
            if (saleChance.getDevResult()==0){
                saleChance.setDevResult(1);
                saleChanceMapper.update(saleChance);
            }
        }
        AssertUtil.isTrue(cusDevPlanMapper.update(cusDevPlan)<1,"更新失败");
    }


    /**
     * 判断参数合法
     * @param cusDevPlan
     */
    private void checkCusDevPlans(CusDevPlan cusDevPlan) {
        AssertUtil.isTrue(cusDevPlan.getPlanDate()==null,"计划时间为空");
        AssertUtil.isTrue(StringUtils.isBlank(cusDevPlan.getPlanItem()),"计划内容为空");
        AssertUtil.isTrue(StringUtils.isBlank(cusDevPlan.getExeAffect()),"执行效果为空");
    }
}
