package com.baihy.service;

import lombok.extern.slf4j.Slf4j;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.UsernamePasswordToken;
import org.apache.shiro.authz.AuthorizationException;
import org.apache.shiro.mgt.SecurityManager;
import org.apache.shiro.subject.Subject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.Assert;

import javax.annotation.PostConstruct;

/**
 * @projectName: shiro-demo
 * @packageName: com.baihy.service
 * @description:
 * @author: huayang.bai
 * @date: 2019/11/4 16:05
 */
@Service
@Slf4j
public class QuickStart {

    @Autowired
    private SecurityManager securityManager;

    @Autowired
    private DemoService demoService;

    public void run() {

        // get the current subject
        Subject subject = SecurityUtils.getSubject();

        // Subject is not authenticated yet
        Assert.isTrue(!subject.isAuthenticated());

        // login the subject with a username / password
        UsernamePasswordToken token = new UsernamePasswordToken("joe.coder", "password");
        subject.login(token);

        // joe.coder has the "user" role
        subject.checkRole("user");

        // joe.coder does NOT have the admin role
        Assert.isTrue(!subject.hasRole("admin"));

        // joe.coder has the "read" permission
        subject.checkPermission("read");
        // current user is allowed to execute this method.
        demoService.readRestrictedCall();
        try {
            // but not this one!
            demoService.writeRestrictedCall();
        } catch (AuthorizationException e) {
            log.info("Subject was NOT allowed to execute method 'writeRestrictedCall'");
        }
        // logout
        subject.logout();
        Assert.isTrue(!subject.isAuthenticated());
    }


    @PostConstruct
    private void initSecurityManager() {
        SecurityUtils.setSecurityManager(this.securityManager);
    }
}
