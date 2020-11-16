package com.data.big.interceptor;

import com.data.big.service.ServiceUser;
import com.data.big.util.Properties;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurationSupport;

@Configuration
public class InterceptorConfig extends WebMvcConfigurationSupport {

    @Autowired
    private LogcostInterceptor logcostInterceptor;
    /**
     * 静态资源配置
     */
    /*@Override
    public void addResourceHandlers(ResourceHandlerRegistry registry) {
        registry.addResourceHandler("/img/**")
                .addResourceLocations("classpath:/imgs/");

        super.addResourceHandlers(registry);
    }*/

    /**
     * 默认首页配置
     */
    /*@Override
    public void addViewControllers(ViewControllerRegistry registry) {
        registry.addViewController("/controllerUser").setViewName("forward:/login");
        registry.setOrder(Ordered.HIGHEST_PRECEDENCE);
        super.addViewControllers(registry);
    }*/

    /**
     * interceptor配置
     */
    @Override
    public void addInterceptors(InterceptorRegistry registry) {
        String userTokenEnable = Properties.getUserTokenEnable();
        if(StringUtils.equals("true",userTokenEnable)){
            registry.addInterceptor(logcostInterceptor)
                    //添加需要验证登录用户操作权限的请求
                    .addPathPatterns("/**")
                    //这里add为“/**”,下面的exclude才起作用，且不管controller层是否有匹配客户端请求，拦截器都起作用拦截
//                .addPathPatterns("/hello")
                    //如果add为具体的匹配如“/hello”，下面的exclude不起作用,且controller层不匹配客户端请求时拦截器不起作用

                    //排除不需要验证登录用户操作权限的请求
                    .excludePathPatterns("/controllerUser/getToken")
                    .excludePathPatterns("/controllerUser/login")
                    .excludePathPatterns("/css/**")
                    .excludePathPatterns("/js/**")
                    .excludePathPatterns("/images/**");
        }
        //这里可以用registry.addInterceptor添加多个拦截器实例，后面加上匹配模式
        super.addInterceptors(registry);//最后将register往这里塞进去就可以了
    }
}