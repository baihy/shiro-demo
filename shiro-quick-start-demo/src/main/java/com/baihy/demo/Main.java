package com.baihy.demo;


import lombok.extern.slf4j.Slf4j;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.*;
import org.apache.shiro.config.IniSecurityManagerFactory;
import org.apache.shiro.mgt.SecurityManager;
import org.apache.shiro.session.Session;
import org.apache.shiro.subject.Subject;

/**
 * @projectName: shiro-demo
 * @packageName: com.baihy.demo
 * @description:
 * @author: huayang.bai
 * @date: 2019/11/2 13:32
 */
@Slf4j
public class Main {

    public static void main(String[] args) {
        log.info("My First Apache Shiro Application");

        IniSecurityManagerFactory factory = new IniSecurityManagerFactory("classpath:shiro.ini");
        SecurityManager securityManager = factory.getInstance();
        SecurityUtils.setSecurityManager(securityManager);

        // get the currently executing user:
        // 获取和shiro交互的门面
        Subject subject = SecurityUtils.getSubject();

        // Do some stuff with a Session (no need for a web or EJB container!!!)
        // 虽说不是web环境，在java se的环境中，模拟session
        Session session = subject.getSession();
        session.setAttribute("someKey", "aValue");
        String value = (String) session.getAttribute("someKey");
        if (value.equals("aValue")) {
            log.info("从session中获取到的值：[" + value + "]");
        }

        // let's login the current user so we can check against roles and permissions:
        if (!subject.isAuthenticated()) { // 判断用户是否已经登录
            // 把登录时用到的用户名和密码封装成token
            UsernamePasswordToken token = new UsernamePasswordToken("lonestarr", "vespa");
            // 记住我
            token.setRememberMe(true);
            try {
                subject.login(token); // 登录操作
            } catch (UnknownAccountException uae) {
                log.info("There is no user with username of " + token.getPrincipal());
            } catch (IncorrectCredentialsException ice) {
                log.info("Password for account " + token.getPrincipal() + " was incorrect!");
            } catch (LockedAccountException lae) {
                log.info("The account for username " + token.getPrincipal() + " is locked.  " +
                        "Please contact your administrator to unlock it.");
            }// ... catch more exceptions here (maybe custom ones specific to your application?
            catch (AuthenticationException ae) {
                // 注意：这几个异常之间的继承关系
                //unexpected condition?  error?
            }
        }

        //say who they are:
        //print their identifying principal (in this case, a username):
        // 获取登录的用户标记，一般是用户名
        log.info("User [" + subject.getPrincipal() + "] logged in successfully.");

        //test a role:
        // 判断用户是否有指定的角色
        if (subject.hasRole("schwartz")) {
            log.info("May the Schwartz be with you!");
        } else {
            log.info("Hello, mere mortal.");
        }

        //test a typed permission (not instance-level)
        // 判断登录用户是否有指定的权限
        if (subject.isPermitted("lightsaber:wield")) {
            log.info("You may use a lightsaber ring.  Use it wisely.");
        } else {
            log.info("Sorry, lightsaber rings are for schwartz masters only.");
        }

        //a (very powerful) Instance Level permission:
        // 判断登录用户是否有指定的权限
        if (subject.isPermitted("winnebago:drive:eagle5")) {
            log.info("You are permitted to 'drive' the winnebago with license plate (id) 'eagle5'.  " +
                    "Here are the keys - have fun!");
        } else {
            log.info("Sorry, you aren't allowed to drive the 'eagle5' winnebago!");
        }
        //all done - log out!
        subject.logout();
        System.exit(0);
    }


}
