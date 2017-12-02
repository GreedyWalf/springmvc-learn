package com.qs.mvc.config;

import org.springframework.web.WebApplicationInitializer;
import org.springframework.web.context.support.AnnotationConfigWebApplicationContext;
import org.springframework.web.servlet.DispatcherServlet;

import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.ServletRegistration;

/**
 * WebApplicationInitializer是Spring提供用来配置Servlet3.0+配置的接口，
 * 从而实现了替代web.xml的位置；
 *
 * 实现此接口将会自动被SpringServletContainerInitializer（用来启动3.0容器）获取到；
 */
public class WebInitializer implements WebApplicationInitializer{

    @Override
    public void onStartup(ServletContext servletContext) throws ServletException {
        AnnotationConfigWebApplicationContext context = new AnnotationConfigWebApplicationContext();
        context.register(MyMvcConfig.class);
        //新建WebApplicationContext，注册配置类，并将其和当前servletContext关联
        context.setServletContext(servletContext);

        //注册SpringMvc的DispatcherServlet
        ServletRegistration.Dynamic servlet = servletContext.addServlet("dispatcher", new DispatcherServlet(context));
        servlet.addMapping("/");
        servlet.setLoadOnStartup(1);
    }
}
