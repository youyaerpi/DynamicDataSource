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
    /**
     * 切点(也可基于注解)
     * 在paper包下切入
     */
    @Pointcut("execution(* com.ecjtu.dynamicdatasource.mapper.paper..*.*(..))")
    private void paperAspect() {
    }
    /**
     * 切点
     * 在aliyun包下切入
     */
    @Pointcut("execution(* com.ecjtu.dynamicdatasource.mapper.aliyun..*.*(..))")
    private void aliYunAspect() {
    }

    /**
     * 方法执行之前
     * 设置paper数据源
     */
    @Before(value = "paperAspect()")
    public void paperBefore() {
        System.out.println("设置数据源paper");
        DbContextHolder.setDbType(DBTypeEnum.PAPER);
    }
    /**
     * 方法执行之前
     * 设置aliyun数据源
     */
    @Before("aliYunAspect()")
    public void aliYunBefore() {
        DbContextHolder.setDbType(DBTypeEnum.ALI_YUN);
        System.out.println("设置数据源aliYun");
    }
    /**
     * 方法执行之后
     * 清除paper数据源
     */

    @After(value = "paperAspect()")
    public void paperAfter() {
        System.out.println("清除数据源paper");
        DbContextHolder.clearDbType();
    }
    /**
     * 方法执行之后
     * 清除aliyun数据源
     */
    @After(value = "aliYunAspect()")
    public void aliYunAfter() {
        DbContextHolder.clearDbType();
        System.out.println("清除数据源aliYun");
    }



}
