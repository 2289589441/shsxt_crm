package com.shsxt.crm.dao;


import com.shsxt.crm.base.BaseDao;
import com.shsxt.crm.po.User;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

/**
 * @author 康晓伟
 */
@Repository
public interface UserMapper extends BaseDao<User> {
    /**
     * 根据用户名查询user
     * @param userName 用户名
     * @return 一个user对象
     */
    User queryUserByName(String userName);

    /**
     * 更新密码
     * @param newPwd
     * @param id
     * @return
     */
    Integer upDateUserPwd(@Param("newPwd") String newPwd,@Param("id") Integer id);

}