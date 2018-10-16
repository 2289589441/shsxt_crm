package com.shsxt.crm.service;

import com.shsxt.crm.base.BaseService;
import com.shsxt.crm.dao.UserMapper;
import com.shsxt.crm.model.UserInfo;
import com.shsxt.crm.po.User;
import com.shsxt.crm.utils.AssertUtil;
import com.shsxt.crm.utils.Md5Util;
import com.shsxt.crm.utils.UserIDBase64;
import org.apache.commons.lang3.StringUtils;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;
import java.util.Map;
import java.util.Objects;

/**
 * @auther: 康晓伟
 * @date: 2018/10/13 11:40
 * @description: shsxt_crm
 */
@Service
public class UserService extends BaseService<User> {

    @Resource
    private UserMapper userMapper;

    /**
     *
     */
    public void upDateUserPwd(String userName,String oldUserPwd,String newUserPwd,String conformUserPwd,Integer id){

        //判断参数
        checkUpDateUserPwd(oldUserPwd,newUserPwd,conformUserPwd);

        // 判断user是否存在
        User user = userMapper.queryUserByName(userName);

        AssertUtil.isTrue(user==null,"用户名不存在或已注销");

        //判断原密码与数据库是否一致
        AssertUtil.isTrue(!user.getUserPwd().equals(Md5Util.encode(oldUserPwd)),"原密码输入错误");

        //更新密码
        AssertUtil.isTrue(userMapper.upDateUserPwd(Md5Util.encode(newUserPwd),id)<1,"更新失败");

    }

    /**
     * 验证修改密码时的参数
     * @param oldUserPwd
     * @param newUserPwd
     * @param conformUserPwd
     */
    private void checkUpDateUserPwd(String oldUserPwd, String newUserPwd, String conformUserPwd) {
        AssertUtil.isTrue(StringUtils.isBlank(oldUserPwd),"原密码为空");
        AssertUtil.isTrue(StringUtils.isBlank(newUserPwd),"新密码为空");
        AssertUtil.isTrue(!newUserPwd.equals(conformUserPwd),"两次密码不一致");
    }

    /**
     * 用户登录操作
     * @param userName 用户名
     * @param userPwd 用户密码
     */
    public UserInfo login(String userName, String userPwd){
        //1判断 userName  userPwd 非空
        AssertUtil.isTrue(StringUtils.isBlank(userName),"用户名为空");
        AssertUtil.isTrue(StringUtils.isBlank(userPwd),"密码为空");

        //2 查询数据库 返回user对象
        User user = userMapper.queryUserByName(userName);

        //3 判断user是否存在
        AssertUtil.isTrue(user==null,"用户名不存在或已注销");

        //4 判断密码是否一致
        AssertUtil.isTrue(!Objects.equals(Md5Util.encode(userPwd), user.getUserPwd()),"用户名或密码错误");

        return createUserIdStr(user);
    }

    /**
     * 登录操作获取userId
     * @param user
     * @return
     */
    private UserInfo createUserIdStr(User user) {
        UserInfo userInfo = new UserInfo();

        userInfo.setUserIdStr(UserIDBase64.encoderUserID(user.getId()));
        return userInfo;
    }

    /**
     * 查询是客户经理
     * @return
     */
    public List<Map<String, Object>> queryCustomerManagers() {
        return userMapper.queryCustomerManagers();
    }
}
