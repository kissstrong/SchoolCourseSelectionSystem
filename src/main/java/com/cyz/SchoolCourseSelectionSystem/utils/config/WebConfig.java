package com.cyz.SchoolCourseSelectionSystem.utils.config;

import com.cyz.SchoolCourseSelectionSystem.utils.handler.LoginInterceptor;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

/**
 * @author cyz
 * @date 2020-07-20 18:12
 */
@Configuration
public class WebConfig implements WebMvcConfigurer {
    @Override
    public void addInterceptors(InterceptorRegistry registry) {
        registry.addInterceptor(new LoginInterceptor())
                .addPathPatterns("/**")
                .excludePathPatterns("/tologin")
                .excludePathPatterns("/toregister")
                .excludePathPatterns("/register")
                .excludePathPatterns("/login")
                .excludePathPatterns("/dist/**")
                .excludePathPatterns("/images/**")
                .excludePathPatterns("/sendcheckCode")
                .excludePathPatterns("/checkcode")
                .excludePathPatterns("/toreset")
                .excludePathPatterns("/checkemail")
                .excludePathPatterns("/existemail")
                .excludePathPatterns("/resetpassword")
                .excludePathPatterns("/logout")
                .excludePathPatterns("/js/**");
    }

}
