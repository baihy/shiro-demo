package com.baihy.service;

import lombok.extern.slf4j.Slf4j;
import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.springframework.stereotype.Service;

/**
 * @projectName: shiro-demo
 * @packageName: com.baihy.config.com.baihy.service
 * @description:
 * @author: huayang.bai
 * @date: 2019/11/4 16:01
 */
@Service
@Slf4j
public class DemoService {


    @RequiresPermissions("write")
    public void writeRestrictedCall() {
        log.info("executing method that requires the 'write' permission");
    }

    @RequiresPermissions("read")
    public void readRestrictedCall() {
        log.info("executing method that requires the 'read' permission");
    }
}
