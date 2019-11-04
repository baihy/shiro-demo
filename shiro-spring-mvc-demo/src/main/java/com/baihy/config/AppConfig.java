package com.baihy.config;

import com.mysql.cj.jdbc.MysqlDataSource;
import org.apache.shiro.spring.config.ShiroAnnotationProcessorConfiguration;
import org.apache.shiro.spring.config.ShiroBeanConfiguration;
import org.apache.shiro.spring.web.config.DefaultShiroFilterChainDefinition;
import org.apache.shiro.spring.web.config.ShiroFilterChainDefinition;
import org.apache.shiro.spring.web.config.ShiroWebConfiguration;
import org.apache.shiro.spring.web.config.ShiroWebFilterConfiguration;
import org.springframework.context.annotation.*;

/**
 * @projectName: shiro-demo
 * @packageName: com.baihy.config
 * @description:
 * @author: huayang.bai
 * @date: 2019/11/4 14:38
 */
@Configuration
@ComponentScan("com.baihy")
@Import({ShiroWebConfiguration.class,
        ShiroBeanConfiguration.class,
        ShiroWebFilterConfiguration.class,
        ShiroAnnotationProcessorConfiguration.class})
@PropertySource("classpath:application.properties")
public class AppConfig {

    @Bean
    protected MysqlDataSource dataSource() {
        MysqlDataSource dataSource = new MysqlDataSource();
        dataSource.setUrl("jdbc:mysql://127.0.0.1:3308/shiro-demo?serverTimezone=Asia/Shanghai&useSSL=false");
        dataSource.setUser("root");
        dataSource.setPassword("root");
        return dataSource;
    }

    @Bean
    public ShiroFilterChainDefinition shiroFilterChainDefinition() {
        DefaultShiroFilterChainDefinition chainDefinition = new DefaultShiroFilterChainDefinition();
        chainDefinition.addPathDefinition("/login", "anon");
        chainDefinition.addPathDefinition("/**", "authc");
        return chainDefinition;
    }


}
