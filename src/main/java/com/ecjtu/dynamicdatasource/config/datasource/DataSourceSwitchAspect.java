package com.ecjtu.dynamicdatasource.config.datasource;

import org.aspectj.lang.annotation.After;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.aspectj.lang.annotation.Pointcut;
import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Component;

/**
 *切面配置
 * @author xiexiang
 */
@Component
@Order(value = -1)
@Aspect
public class DataSourceSwitchAspect {

    @Pointcut("execution(* com.ecjtu.dynamicdatasource.mapper.paper..*.*(..))")
    private void paperAspect() {
    }

    @Pointcut("execution(* com.ecjtu.dynamicdatasource.mapper.aliyun..*.*(..))")
    private void aliYunAspect() {
    }

    @Before(value = "paperAspect()")
    public void paperBefore() {
        System.out.println("设置数据源paper");
        DbContextHolder.setDbType(DBTypeEnum.PAPER);
    }

    @Before("aliYunAspect()")
    public void aliYunBefore() {
        DbContextHolder.setDbType(DBTypeEnum.ALI_YUN);
        System.out.println("设置数据源aliYun");
    }


    @After(value = "paperAspect()")
    public void paperAfter() {
        System.out.println("清除数据源paper");
        DbContextHolder.clearDbType();
    }

    @After(value = "aliYunAspect()")
    public void aliYunAfter() {
        DbContextHolder.clearDbType();
        System.out.println("清除数据源aliYun");
    }



}
