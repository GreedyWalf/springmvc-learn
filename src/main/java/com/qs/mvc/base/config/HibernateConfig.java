package com.qs.mvc.base.config;

import com.mchange.v2.c3p0.ComboPooledDataSource;
import org.apache.log4j.Logger;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;
import org.springframework.context.support.PropertySourcesPlaceholderConfigurer;
import org.springframework.orm.hibernate4.HibernateTransactionManager;
import org.springframework.orm.hibernate4.LocalSessionFactoryBean;
import org.springframework.transaction.interceptor.TransactionInterceptor;

import javax.sql.DataSource;
import java.beans.PropertyVetoException;
import java.util.Properties;

@Configuration
@PropertySource("classpath:env.properties")
public class HibernateConfig {

    private final static Logger LOG = Logger.getLogger(HibernateConfig.class);

    @Value("${jdbc.url}")
    private String jdbcUrl;

    @Value("${jdbc.driverClassName}")
    private String jdbcDriver;

    @Value("${jdbc.username}")
    private String userName;

    @Value("${jdbc.password}")
    private String password;

    @Value("${hibernate.dialect}")
    private String hbmDialect;

    @Value("${hibernate.hbm2ddl.auto}")
    private String hbm2ddlAuto;

    @Value("${hibernate.show_sql}")
    private String showSql;

    @Value("${hibernate.format_sql}")
    private String formatSql;


    @Bean
    public DataSource getDataSourse() {
        ComboPooledDataSource dataSource = null;
        try {
            dataSource = new ComboPooledDataSource();
            dataSource.setJdbcUrl(jdbcUrl);
            dataSource.setDriverClass(jdbcDriver);
            dataSource.setUser(userName);
            dataSource.setPassword(password);
        } catch (PropertyVetoException e) {
            LOG.error("get dataSource occur exception", e);
        }

        return dataSource;
    }

    @Bean(name = "sessionFactory")
    public LocalSessionFactoryBean localSessionFactoryBean() {
        LocalSessionFactoryBean sessionFactoryBean = new LocalSessionFactoryBean();
        sessionFactoryBean.setDataSource(getDataSourse());

        //扫描实体类
        sessionFactoryBean.setPackagesToScan("com.qs.mvc.entity");
        Properties hbmProperties = new Properties();
        hbmProperties.setProperty("hibernate.dialect", hbmDialect);
        hbmProperties.setProperty("hibernate.hbm2ddl.auto", hbm2ddlAuto);
        hbmProperties.setProperty("hibernate.show_sql", showSql);
        hbmProperties.setProperty("hibernate.format_sql", formatSql);

        sessionFactoryBean.setHibernateProperties(hbmProperties);
        return sessionFactoryBean;
    }

    public SessionFactory sessionFactory(){
        return localSessionFactoryBean().getObject();
    }

//    @Bean(name = "transactionManager")
//    public HibernateTransactionManager hibernateTransactionManager(){
//        HibernateTransactionManager hbmTransactionManager = new HibernateTransactionManager();
//        hbmTransactionManager.setDataSource(getDataSourse());
//        hbmTransactionManager.setSessionFactory(localSessionFactoryBean().getObject());
//        return hbmTransactionManager;
//    }
//
//    @Bean(name="transactionInterceptor")
//    public TransactionInterceptor interceptor(){
//        TransactionInterceptor interceptor = new TransactionInterceptor();
//        interceptor.setTransactionManager(hibernateTransactionManager());
//
//        Properties transactionAttributes = new Properties();
//        transactionAttributes.setProperty("save*", "PROPAGATION_REQUIRED");
//        transactionAttributes.setProperty("del*", "PROPAGATION_REQUIRED");
//        transactionAttributes.setProperty("update*", "PROPAGATION_REQUIRED");
//        transactionAttributes.setProperty("get*", "PROPAGATION_REQUIRED,readOnly");
//        transactionAttributes.setProperty("find*", "PROPAGATION_REQUIRED,readOnly");
//        transactionAttributes.setProperty("*", "PROPAGATION_REQUIRED");
//
//        interceptor.setTransactionAttributes(transactionAttributes);
//        return interceptor;
//    }

    /**
     * 使用@Value注解 需要注入propertySourcesPlaceholderConfigurer对象（spring3.0之后推荐使用）
     */
    @Bean
    public static PropertySourcesPlaceholderConfigurer propertyConfigure() {
        return new PropertySourcesPlaceholderConfigurer();
    }
}
