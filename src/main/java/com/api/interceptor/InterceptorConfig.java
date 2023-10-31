package com.api.interceptor;

import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

/**
 * 拦截器配置类
 *
 * @author 周建泽
 * @date 2022/5/31
 */
@Configuration
public class InterceptorConfig implements WebMvcConfigurer {

    private final UserInterceptor userInterceptor;

    public InterceptorConfig(UserInterceptor userInterceptor) {
        this.userInterceptor = userInterceptor;
    }

    @Override
    public void addInterceptors(InterceptorRegistry registry) {

        //拦截器路径处理 拦截所有路径
        registry.addInterceptor(userInterceptor)
                .addPathPatterns("/api/**")
                //不需要拦截的请求 -登录请求
                .excludePathPatterns("/api/login")
                .excludePathPatterns("/api/listCacheUserInfo")
        ;
    }

    //配置静态资源
    @Override
    public void addResourceHandlers(ResourceHandlerRegistry registry) {
        registry.addResourceHandler("/**")
                .addResourceLocations("classpath:/resources/");
    }
}
