package com.qs.mvc.base.config;

import org.apache.log4j.Logger;
import org.springframework.web.WebApplicationInitializer;
import org.springframework.web.context.support.AnnotationConfigWebApplicationContext;
import org.springframework.web.servlet.DispatcherServlet;
import org.springframework.web.servlet.support.AbstractAnnotationConfigDispatcherServletInitializer;

import javax.servlet.MultipartConfigElement;
import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.ServletRegistration;

/**
 * WebApplicationInitializer是Spring提供用来配置Servlet3.0+配置的接口，
 * 从而实现了替代web.xml的位置；
 *
 * 实现此接口将会自动被SpringServletContainerInitializer（用来启动3.0容器）获取到；
 */
public class WebAppInitializer extends AbstractAnnotationConfigDispatcherServletInitializer {
    private final static Logger LOG = Logger.getLogger(WebAppInitializer.class);

    //指定配置类
    @Override
    protected Class<?>[] getRootConfigClasses() {
        LOG.info("------load RootConfig.class-------------");
        return new Class<?>[]{RootConfig.class};
    }

    @Override
    protected Class<?>[] getServletConfigClasses() {
        LOG.info("------load WebConfig.class-------------");
        return new Class<?>[]{WebConfig.class};
    }

    //将DispatchServlet映射到“/”
    @Override
    protected String[] getServletMappings() {
        return new String[]{"/"};
    }
}
