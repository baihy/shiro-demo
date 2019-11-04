package com.baihy;

import com.baihy.service.QuickStart;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ConfigurableApplicationContext;

/**
 * @projectName: shiro-demo
 * @packageName: com.baihy
 * @description:
 * @author: huayang.bai
 * @date: 2019/11/4 16:03
 */
@SpringBootApplication
public class ApplicationMain {

    public static void main(String[] args){

        ConfigurableApplicationContext applicationContext = SpringApplication.run(ApplicationMain.class, args);
        QuickStart quickStart = applicationContext.getBean(QuickStart.class);
        quickStart.run();
    }

}
