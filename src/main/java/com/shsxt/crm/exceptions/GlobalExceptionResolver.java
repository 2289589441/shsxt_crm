package com.shsxt.crm.exceptions;

import com.alibaba.fastjson.JSON;
import com.shsxt.crm.model.ResultInfo;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.method.HandlerMethod;
import org.springframework.web.servlet.HandlerExceptionResolver;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;

/**
 * @author 康晓伟
 * @auther: 康晓伟
 * @date: 2018/10/15 09:27
 * @description: shsxt_crm
 */
@Component
public class GlobalExceptionResolver implements HandlerExceptionResolver {
    @Override
    public ModelAndView resolveException(HttpServletRequest request, HttpServletResponse response, Object handler, Exception ex) {
        /*获取ModelAndView对象，并设置默认信息*/
        ModelAndView modelAndView = createModelAndView(request);

        if(ex instanceof NotLoginException){
            modelAndView.addObject("errorMsg",((NotLoginException) ex).getMsg());
        }
        /*
        * 1. 区分是什么异常
        * 2. 区分是页面请求还是json请求
        */
        if(handler instanceof HandlerMethod){
            HandlerMethod method = (HandlerMethod) handler;
            ResponseBody responseBody = method.getMethodAnnotation(ResponseBody.class);
            /*页面请求没有ResponseBody注解*/
            if (responseBody == null) {
                if(ex instanceof ParamsException){
                    modelAndView.addObject("errorMsg",((ParamsException) ex).getMsg());
                }
            }else {
                /*json请求 返回ResultInfo对象*/
                ResultInfo resultInfo = new ResultInfo();
                resultInfo.setCode(300);
                resultInfo.setMsg("出现错误啦");

                if(ex instanceof ParamsException){
                    resultInfo.setMsg(((ParamsException) ex).getMsg());
                }
                response.setCharacterEncoding("utf-8");
                response.setContentType("application/json;charset=utf-8");
                try (PrintWriter writer = response.getWriter()) {
                    writer.write(JSON.toJSONString(resultInfo));
                    writer.flush();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
        return modelAndView;
    }

    private ModelAndView createModelAndView(HttpServletRequest request) {
        ModelAndView mv= new ModelAndView();
        // 错误页面
        mv.setViewName("error");
        // 错误信息
        mv.addObject("errorMsg", "系统繁忙");
        // 错误码
        mv.addObject("errorCode", 300);
        // 上下文路径
        mv.addObject("ctx", request.getContextPath());
        return mv;
    }
}
