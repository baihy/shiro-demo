package com.baihy.domain;

import lombok.Data;
import lombok.experimental.Accessors;

/**
 * @projectName: shiro-demo
 * @packageName: com.baihy.domain
 * @description:
 * @author: huayang.bai
 * @date: 2019/11/4 14:58
 */
@Data
@Accessors(chain = true)
public class User {

    private Integer id;

    private String username;

    private String password;

}


