package com.baihy.temp;

import com.baihy.domain.User;

import java.util.ArrayList;
import java.util.List;

/**
 * @projectName: shiro-demo
 * @packageName: com.baihy.temp
 * @description:
 * @author: huayang.bai
 * @date: 2019/11/4 14:57
 */
public class UserData {


    public static List<User> USERS = null;

    static {

        USERS = new ArrayList<>();
        USERS.add(new User().setId(1).setUsername("admin").setPassword("admin"));
        USERS.add(new User().setId(2).setUsername("admin1").setPassword("admin1"));
        USERS.add(new User().setId(3).setUsername("admin2").setPassword("admin2"));

    }


}
