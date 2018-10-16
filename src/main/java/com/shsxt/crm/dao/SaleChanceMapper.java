package com.shsxt.crm.dao;

import com.shsxt.crm.base.BaseDao;
import com.shsxt.crm.po.SaleChance;
import org.springframework.stereotype.Repository;


/**
 * @author 康晓伟
 */
@Repository
public interface SaleChanceMapper extends BaseDao<SaleChance> {

    /**
     * 更新开发状态
     * @param saleChance
     * @return
     */
    Integer updateSaleChanceDevResult(SaleChance saleChance);
}