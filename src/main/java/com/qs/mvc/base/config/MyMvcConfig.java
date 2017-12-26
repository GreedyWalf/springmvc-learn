package com.qs.mvc.base.config;

import com.qs.mvc.base.interceptor.DemoInterceptor;
import com.qs.mvc.base.interceptor.LoginInterceptor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.converter.HttpMessageConverter;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.web.multipart.MultipartResolver;
import org.springframework.web.multipart.commons.CommonsMultipartResolver;
import org.springframework.web.servlet.config.annotation.*;
import org.springframework.web.servlet.view.InternalResourceViewResolver;
import org.springframework.web.servlet.view.JstlView;
import org.springframework.web.servlet.view.freemarker.FreeMarkerConfigurer;
import org.springframework.web.servlet.view.freemarker.FreeMarkerView;
import org.springframework.web.servlet.view.freemarker.FreeMarkerViewResolver;

import java.util.List;
import java.util.Properties;

/**
 * 此处无任何特别，只是一个普通的Spring陪着孩子类；
 * 这里配置了一个jsp的ViewResolver，用来映射路径和实际页面的位置，其中@EnableWebMvc注解会开启默认设置
 * 如：ViewResolver或者MessageConverter等；
 * <p>
 * 注意点：
 *  1、@EnableWebMvc开启SpringMVc支持，若没有此注解，重写WebMvcConfigurerAdapter类中方法无效；
 *  2、继承了WebMvcConfigurerAdapter，重写其方法可以对SpringMvc进行配置；
 */
@Configuration
@EnableWebMvc
@EnableScheduling  //在MyMvcConfig上开始支持计划任务的支持
@ComponentScan("com.qs.mvc")
public class MyMvcConfig extends WebMvcConfigurerAdapter {

    /**
     * SpringMvc的接口ViewResolver，这个方法的返回值是接口view；
     * View的职责就是使用model、request、response对象，并将渲染的视图（可能是html，
     * 也可能返回json、xml、pdf）返回给浏览器；
     */
    @Bean
    public InternalResourceViewResolver viewResolver(){
        InternalResourceViewResolver viewResolver = new InternalResourceViewResolver();
        //设置静态资源的映射路径（对应resource目录中的views文件夹）
        viewResolver.setPrefix("/WEB-INF/classes/views/");
        //设置访问静态资源的后缀名
        viewResolver.setSuffix(".jsp");
        viewResolver.setViewClass(JstlView.class);
        return viewResolver;
    }

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
        configurer.setTemplateLoaderPath("/WEB-INF/classes/views/ftl/");
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
     * 配置拦截器的Bean
     */
    @Bean
    public DemoInterceptor doDemoInterceptor() {
        return new DemoInterceptor();
    }

    /**
     * 配置登录拦截器Bean
     */
    @Bean
    public LoginInterceptor doLoginInterceptor(){
        return new LoginInterceptor();
    }

    //重写WebMvcConfigurerAdapter类的addInterceptors方法，注册拦截器；
    @Override
    public void addInterceptors(InterceptorRegistry registry) {
        registry.addInterceptor(doDemoInterceptor());
        registry.addInterceptor(doLoginInterceptor());
    }


    /**
     * 设置页面
     * 如果仅仅只是为了进行页面跳转，可以实现addViewControllers方法，设置请求路径及对应返回的页面；
     */
    @Override
    public void addViewControllers(ViewControllerRegistry registry) {
        //http://localhost:8080/mvc/index
        registry.addViewController("/index").setViewName("/index");
        //http://localhost:8080/mvc/index/index
        registry.addViewController("/index/index").setViewName("/index");

        //跳转到文件上传页面
        registry.addViewController("/toUpload").setViewName("/upload");
        //测试自定义的HttpMessageConverter
        registry.addViewController("/converter").setViewName("/converter");
        //测试远程推送服务--SSE
        registry.addViewController("/sse").setViewName("/sse");
        //测试远程推送服务--异步方法实现形式
        registry.addViewController("/async").setViewName("/async");
    }

    /**
     * 路径参数带"."后面的值被忽略，可以实现configurePathMatch方法，设置路径参数中保留"."后面的值
     * 测试： 访问 http://localhost:8080/mvc/anno/pathvar/zhangsan.yy
     * 返回的结果：http://localhost:8080/mvc/anno/pathvar/zhangsan.yycan access,str=zhangsan.yy
     */
    @Override
    public void configurePathMatch(PathMatchConfigurer configurer) {
        //设置路径参数中不过滤"."后面的值
        configurer.setUseSuffixPatternMatch(false);
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

    /**
     * 重写extendMessageConverters，将自定义的HttpMessageConverter注入到配置中；
     * （可以自定义媒体类型，可以实现将response中响应的内容加密等场景）
     */
    @Override
    public void extendMessageConverters(List<HttpMessageConverter<?>> converters) {
        converters.add(converter());
    }

    @Bean
    public MyMessageConverter converter() {
        return new MyMessageConverter();
    }
}