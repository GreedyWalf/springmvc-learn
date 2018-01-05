package com.qs.mvc.base.config;

import com.qs.mvc.base.interceptor.LoginInterceptor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.multipart.MultipartResolver;
import org.springframework.web.multipart.commons.CommonsMultipartResolver;
import org.springframework.web.servlet.config.annotation.*;
import org.springframework.web.servlet.view.freemarker.FreeMarkerConfigurer;
import org.springframework.web.servlet.view.freemarker.FreeMarkerView;
import org.springframework.web.servlet.view.freemarker.FreeMarkerViewResolver;

import java.util.Properties;

/**
 * WebMvcConfig用于定义DispatcherServlet加载应用上下文的配置，主要包含一些web组件
 *
 * 配置类，用于定义DispatcherServlet上线文的bean
 * 这里配置了一个jsp的ViewResolver，用来映射路径和实际页面的位置，其中@EnableWebMvc注解会开启默认设置
 * 如：ViewResolver或者MessageConverter等；
 * <p>
 * 注意点：
 *  1、@EnableWebMvc开启SpringMVc支持，若没有此注解，重写WebMvcConfigurerAdapter类中方法无效；
 *  2、继承了WebMvcConfigurerAdapter，重写其方法可以对SpringMvc进行配置；
 */
@Configuration
@EnableWebMvc
@ComponentScan("com.qs.mvc")
public class WebMvcConfig extends WebMvcConfigurerAdapter {
    /**
     * 配置使用freemarker视图解析器
     */
    @Bean
    public FreeMarkerViewResolver freeMarkerViewResolver() {
        FreeMarkerViewResolver viewResolver = new FreeMarkerViewResolver();
        viewResolver.setCache(false);
        viewResolver.setSuffix(".ftl");
        viewResolver.setContentType("text/html; charset=UTF-8");
        //此变量值为pageContext.request, ftl页面使用方法：Request.contextPath，用来定义请求的前缀${ctx}
        viewResolver.setRequestContextAttribute("Request");
        viewResolver.setOrder(1);
        viewResolver.setViewClass(FreeMarkerView.class);
        return viewResolver;
    }

    /**
     * 配置Freemarker模板路径
     */
    @Bean
    public FreeMarkerConfigurer freeMarkerConfigurer() {
        FreeMarkerConfigurer configurer = new FreeMarkerConfigurer();
        //设置使用freemarker模板所在目录
        configurer.setTemplateLoaderPath("/WEB-INF/classes/views/");
        //设置freemarker模板默认使用编码UTF-8
        configurer.setDefaultEncoding("UTF-8");

        //设置freemarker模板的全局设置
        Properties properties = new Properties();
        properties.setProperty("template_update_delay", "10");
        properties.setProperty("locale", "zh_ch");
        properties.setProperty("datetime_format", "yyyy-MM-dd HH:mm:ss");
        properties.setProperty("date_format", "yyyy-MM-dd");
        properties.setProperty("number_format", "#.##");
        configurer.setFreemarkerSettings(properties);
        return configurer;
    }

    @Override
    public void addResourceHandlers(ResourceHandlerRegistry registry) {
        //将真实的文件放置目录通过addResourceHandler方法对外暴露
        registry.addResourceHandler("/assets/**").addResourceLocations("classpath:/assets/");
    }


    /**
     * 重写WebMvcConfigurerAdapter类的addInterceptors方法，注册拦截器；
     * @param registry
     */
    @Override
    public void addInterceptors(InterceptorRegistry registry) {
        registry.addInterceptor(doLoginInterceptor());
    }

    /**
     * 配置登录拦截器Bean
     */
    @Bean
    public LoginInterceptor doLoginInterceptor(){
        return new LoginInterceptor();
    }


    /**
     * 设置页面
     *
     * 如果仅仅只是为了进行页面跳转，可以实现addViewControllers方法，设置请求路径及对应返回的页面；
     */
    @Override
    public void addViewControllers(ViewControllerRegistry registry) {
        //跳转到登陆页面
        registry.addViewController("/login/login").setViewName("/login/login");
    }

    /**
     * SpringMvc提供配置multipartResolver来实现文件上上传（该对象包含上传的文件的数据信息）
     */
    @Bean
    public MultipartResolver multipartResolver() {
        CommonsMultipartResolver multipartResolver = new CommonsMultipartResolver();
        multipartResolver.setMaxUploadSize(10 * 1024 * 1024);
        return multipartResolver;
    }
}
