package com.ecjtu.dynamicdatasource.util;

import org.springframework.beans.BeansException;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;
import org.springframework.stereotype.Component;

/**
 * @author xiexiang
 * @date 2019/10/28 2:16 下午
 */

@Component
public class SpringContextUtil implements ApplicationContextAware {

    @Value("${mybatis-plus.config-location}")
    public  String mapperLocations;


    public static  ApplicationContext APPLICATION_CONTEXT;


    @Override
    public void setApplicationContext(ApplicationContext applicationContext) throws BeansException {
        APPLICATION_CONTEXT=applicationContext;
    }
}
