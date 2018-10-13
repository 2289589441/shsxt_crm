package com.shsxt.crm.base;

import com.shsxt.crm.model.ResultInfo;
import org.apache.commons.lang3.StringUtils;
import org.springframework.web.bind.annotation.ModelAttribute;

import javax.servlet.http.HttpServletRequest;

/**
 * @author 康晓伟
 * @auther: 康晓伟
 * @date: 2018/10/13 17:44
 * @description: shsxt_crm
 */
public class BaseController {
    /**
     * 加载资源路径
     * @param request HttpServletRequest
     */
    @ModelAttribute
    public void firstMethod(HttpServletRequest request){
        request.setAttribute("ctx",request.getContextPath());
    }

    /**
     * 请求成功返回响应
     * @param code 状态码
     * @param msg 响应信息
     * @param result object
     * @return ResultInfo对象
     */
    public ResultInfo success(Integer code,String msg,Object result){
        ResultInfo resultInfo = new ResultInfo();
        if(!StringUtils.isBlank(msg)){
            resultInfo.setMsg(msg);
        }
        if(code!=null||code>0){
            resultInfo.setCode(code);
        }
        if (result != null) {
            resultInfo.setResult(result);
        }
        return resultInfo;
    }

    /**
     * 请求成功返回响应
     * @param code 状态码
     * @return ResultInfo对象
     */
    public ResultInfo success(Integer code){
        return success(code,null,null);
    }
    /**
     * 请求成功返回响应
     * @param msg 响应信息
     * @return ResultInfo对象
     */
    public ResultInfo success(String msg){
        return success(null,msg,null);
    }
    /**
     * 请求成功返回响应
     * @param result object
     * @return ResultInfo对象
     */
    public ResultInfo success(Object result){
        return success(null,null,result);
    }
    /**
     * 请求成功返回响应
     * @param code 状态码
     * @param msg 响应信息
     * @return ResultInfo对象
     */
    public ResultInfo success(Integer code,String msg){
        return success(code,msg,null);
    }
    /**
     * 请求成功返回响应
     * @param code 状态码
     * @param result object
     * @return ResultInfo对象
     */
    public ResultInfo success(Integer code,Object result){

        return success(code,null,result);
    }
    /**
     * 请求成功返回响应
     * @param msg 响应信息
     * @param result object
     * @return ResultInfo对象
     */
    public ResultInfo success(String msg,Object result){
        return success(null,msg,result);
    }
}
