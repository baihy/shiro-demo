package com.baihy.service;

import lombok.extern.slf4j.Slf4j;
import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.springframework.stereotype.Service;

/**
 * @projectName: shiro-demo
 * @packageName: com.baihy.service
 * @description:
 * @author: huayang.bai
 * @date: 2019/11/4 13:59
 */
@Service
@Slf4j
public class DemoService {

    @RequiresPermissions("write")
    public void writeRestrictedCall() {
        log.info("执行改方法需要写权限");
    }

    @RequiresPermissions("read")
    public void readRestrictedCall() {
        log.info("执行改方法需要读权限");
    }

}
