package com.baihy.controller;

import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.AuthenticationException;
import org.apache.shiro.authc.UsernamePasswordToken;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @projectName: shiro-demo
 * @packageName: com.baihy.controller
 * @description:
 * @author: huayang.bai
 * @date: 2019/11/4 15:13
 */
@RestController
public class LoginController {

    @RequestMapping("/login")
    public String login(String username, String password) {
        UsernamePasswordToken token = new UsernamePasswordToken(username, password);
        try {
            SecurityUtils.getSubject().login(token);
        } catch (AuthenticationException e) {
            System.out.println("登录失败！！！！");
            return "登录失败";
        }
        System.out.println("登录成功！！！！");
        return "登录成功";
    }


    @RequestMapping("/unauthorized")
    public String unauthorizedUrl() {
        System.out.println("unauthorizedPage！！！！");
        return "unauthorizedPage";
    }

}
