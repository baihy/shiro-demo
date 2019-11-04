package com.baihy.realm;

import com.baihy.domain.User;
import com.baihy.temp.UserData;
import org.apache.shiro.authc.*;
import org.apache.shiro.realm.jdbc.JdbcRealm;
import org.springframework.stereotype.Component;

/**
 * @projectName: shiro-demo
 * @packageName: com.baihy.realm
 * @description:
 * @author: huayang.bai
 * @date: 2019/11/4 14:54
 */
@Component
public class MyJdbcRealm extends JdbcRealm {

    @Override
    protected AuthenticationInfo doGetAuthenticationInfo(AuthenticationToken authenticationToken) throws AuthenticationException {
        UsernamePasswordToken token = (UsernamePasswordToken) authenticationToken;
        String username = token.getUsername();
        User user = UserData.USERS.parallelStream().filter(u -> username.equals(u.getUsername())).findFirst().get();
        return new SimpleAuthenticationInfo(username, user.getPassword(), getName());
    }
}
