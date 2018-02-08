package com.qs.mvc.base.config;

import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.FilterType;
import org.springframework.context.annotation.Import;
import org.springframework.core.type.filter.RegexPatternTypeFilter;

import java.util.regex.Pattern;

@Configuration
@Import(HibernateConfig.class)//导入Hibernate配置文件
@ComponentScan(basePackages = {"com.qs.mvc"},
        excludeFilters = {
                @ComponentScan.Filter(type = FilterType.CUSTOM, value = RootConfig.WebPackage.class)
        }
)
public class RootConfig {
    //扫描除了web以外的所有包
    public static class WebPackage extends RegexPatternTypeFilter {
        public WebPackage() {
            super(Pattern.compile("com.qs.mvc\\.config"));
        }
    }
}