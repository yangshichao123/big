package com.data.big.interceptor;

import com.alibaba.fastjson.JSONObject;
import com.data.big.service.Service;
import com.data.big.service.ServiceUser;
import com.data.big.token.TokenTools;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.lang.Nullable;
import org.springframework.stereotype.Component;
import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.Map;
@Component
public class LogcostInterceptor implements HandlerInterceptor {
    @Autowired
    private ServiceUser serviceUser;
    long start = System.currentTimeMillis();
    //preHandle是在请求执行前执行的
    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
        start = System.currentTimeMillis();
        String token=request.getParameter("token");
        String userId=(String)request.getSession().getAttribute("userId");
        boolean b=false;
        if(StringUtils.isEmpty(token)){
            responseOutWithJson(response,"token错误请检查token！");
            return b;
        }
        if(StringUtils.isEmpty(userId)){
            responseOutWithJson(response,"没有登陆 请先登陆！");
            return b;
        }

        Map<String,String> stringMap = serviceUser.judgeTokenIsEqual(token, userId);
        if(stringMap!=null&&StringUtils.equals(stringMap.get("status"),"1")){
            responseOutWithJson(response,stringMap.get("message"));
        }else{
            b=true;
        }
        return b;
       /* //返回true,postHandler和afterCompletion方法才能执行
        // 否则false为拒绝执行，起到拦截器控制作用
        System.out.println("ddddddddddddddddddddddddddddddd");
        return true;*/
    }

    //postHandler是在请求结束之后,视图渲染之前执行的,但只有preHandle方法返回true的时候才会执行
    @Override
    public void postHandle(HttpServletRequest request, HttpServletResponse response, Object handler, @Nullable ModelAndView modelAndView) throws Exception {
        System.out.println("Interception cost="+(System.currentTimeMillis()-start));
    }

    //afterCompletion是视图渲染完成之后才执行,同样需要preHandle返回true，
    @Override
    public void afterCompletion(HttpServletRequest request, HttpServletResponse response, Object handler, @Nullable Exception ex) throws Exception {
        //该方法通常用于清理资源等工作
        System.out.println("afterCompletion");
    }


    /**
     * 以JSON格式输出
     * @param response
     */
    protected void responseOutWithJson(HttpServletResponse response,
                                       Object responseObject) {
        //将实体对象转换为JSON Object转换
        String responseJSONObject = JSONObject.toJSONString(responseObject);
        response.setCharacterEncoding("UTF-8");
        response.setContentType("application/json; charset=utf-8");
        PrintWriter out = null;
        try {
            out = response.getWriter();
            out.append(responseJSONObject);
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            if (out != null) {
                out.close();
            }
        }
    }
}