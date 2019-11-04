package com.baihy;

import com.baihy.config.SpringAppConfig;
import com.baihy.service.DemoService;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.UsernamePasswordToken;
import org.apache.shiro.mgt.SecurityManager;
import org.apache.shiro.subject.Subject;
import org.apache.shiro.util.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

/**
 * @projectName: shiro-demo
 * @packageName: com.baihy
 * @description:
 * @author: huayang.bai
 * @date: 2019/11/4 14:05
 */

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(classes = {SpringAppConfig.class})
public class Main {

    @Autowired
    private SecurityManager securityManager;

    @Autowired
    private DemoService demoService;

    @Test
    public void testLogin() {
        SecurityUtils.setSecurityManager(securityManager);
        Subject subject = SecurityUtils.getSubject();
        System.out.println("是否已经登录：" + subject.isAuthenticated());
        String username = "huayang.bai";
        String password = "123456";
        UsernamePasswordToken token = new UsernamePasswordToken(username, password);
        subject.login(token); //登录
        System.out.println("是否已经登录：" + subject.isAuthenticated());

        // joe.coder has the "user" role
        subject.checkRole("user");

        // 检查当前用户是否有admin角色
        Assert.isTrue(!subject.hasRole("admin"));

        // joe.coder has the "read" permission
        subject.checkPermission("read");
        demoService.readRestrictedCall();
        // logout
        subject.logout();
    }


}
