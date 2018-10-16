package com.shsxt.crm.service;

import com.shsxt.crm.base.BaseService;
import com.shsxt.crm.dao.SaleChanceMapper;
import com.shsxt.crm.dao.SaleChanceMapper;
import com.shsxt.crm.dao.UserMapper;
import com.shsxt.crm.po.SaleChance;
import com.shsxt.crm.utils.AssertUtil;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.Date;

/**
 * @author 康晓伟
 * @auther: 康晓伟
 * @date: 2018/10/15 15:18
 * @description: shsxt_crm
 */
@Service
public class SaleChanceService extends BaseService<SaleChance> {
    @Resource
    private SaleChanceMapper saleChanceMapper;
    @Resource
    private UserMapper userMapper;

    /**
     * 添加或更新操作
     * @param saleChance
     */
    public void saveOrUpdateSaleChance(SaleChance saleChance, Integer id) {
        /*判断参数*/
        chickSaleChance(saleChance);
        /*判断是更新操作 还是添加操作*/
        if (saleChance.getId() == null) {
            /*添加操作 补全参数*/
            if (saleChance.getAssignMan() == null) {
                saleChance.setState(0);
            }else {
                saleChance.setAssignTime(new Date());
                saleChance.setState(1);
            }
            saleChance.setCreateMan(userMapper.queryById(id).getUserName());
            saleChance.setCreateDate(new Date());
            saleChance.setDevResult(0);
            saleChance.setIsValid(1);
            saleChance.setUpdateDate(new Date());

            /*调用方法添加*/
            AssertUtil.isTrue(saleChanceMapper.save(saleChance)<1,"添加失败");
        }else{
            saleChance.setUpdateDate(new Date());
            /*调用方法添加*/
            AssertUtil.isTrue(saleChanceMapper.update(saleChance)<1,"更新失败");
        }
    }

    /**
     * 添加或更新操作 参数验证
     * @param saleChance
     */
    private void chickSaleChance(SaleChance saleChance) {
        AssertUtil.isTrue(StringUtils.isBlank(saleChance.getCustomerName()),"客户名为空");
        AssertUtil.isTrue(StringUtils.isBlank(saleChance.getLinkMan()),"联系人为空");
        AssertUtil.isTrue(StringUtils.isBlank(saleChance.getLinkPhone()),"联系电话为空");
    }

    /**
     * 更新开发状态
     * @param saleChance
     * @return
     */
    public Integer updateSaleChanceDevResult(SaleChance saleChance){
        return saleChanceMapper.updateSaleChanceDevResult(saleChance);
    }
}
