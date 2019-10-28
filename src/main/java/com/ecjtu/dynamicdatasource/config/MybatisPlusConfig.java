//package com.ecjtu.dynamicdatasource.config;
//
//import com.alibaba.druid.spring.boot.autoconfigure.DruidDataSourceBuilder;
//import com.baomidou.mybatisplus.core.MybatisConfiguration;
//import com.baomidou.mybatisplus.extension.plugins.PaginationInterceptor;
//import com.baomidou.mybatisplus.extension.spring.MybatisSqlSessionFactoryBean;
//import com.ecjtu.dynamicdatasource.config.datasource.DBTypeEnum;
//import com.ecjtu.dynamicdatasource.config.datasource.DbContextHolder;
//import com.google.common.collect.Maps;
//import org.apache.ibatis.plugin.Interceptor;
//import org.apache.ibatis.session.SqlSessionFactory;
//import org.apache.ibatis.type.JdbcType;
//import org.mybatis.spring.annotation.MapperScan;
//import org.springframework.beans.BeansException;
//import org.springframework.beans.factory.annotation.Qualifier;
//import org.springframework.beans.factory.annotation.Value;
//import org.springframework.boot.context.properties.ConfigurationProperties;
//import org.springframework.boot.jdbc.DataSourceBuilder;
//import org.springframework.context.ApplicationContext;
//import org.springframework.context.ApplicationContextAware;
//import org.springframework.context.annotation.Bean;
//import org.springframework.context.annotation.Configuration;
//import org.springframework.context.annotation.Primary;
//import org.springframework.jdbc.datasource.lookup.AbstractRoutingDataSource;
//
//import javax.sql.DataSource;
//import javax.validation.constraints.NotNull;
//import java.util.Map;
//
///**
// * mybatis-plus配置类
// *
// * @author xiexiang
// * @date 2019/10/25 2:14 下午
// */
//@Configuration
//@MapperScan(value = "com.ecjtu.dynamicdatasource.mapper.*",sqlSessionTemplateRef = "")
//public class MybatisPlusConfig implements ApplicationContextAware {
//
//    private ApplicationContext applicationContext;
//
//    @Value("${mybatis-plus.config-location}")
//    private String mapperLocations;
//
//
//    /**
//     * mybatis-plus分页插件<br>
//     * 文档：http://mp.baomidou.com<br>
//     */
//    @Bean
//    public PaginationInterceptor paginationInterceptor() {
//        PaginationInterceptor paginationInterceptor = new PaginationInterceptor();
//        paginationInterceptor.setLimit(5000);
//        paginationInterceptor.setDialectType("mysql");
//        return paginationInterceptor;
//    }
//
//    /**
//     * 配置mybatis的sqlSessionFactory
//     *
//     * @return {@link SqlSessionFactory}
//     * @throws {@link Exception}
//     */
//    @Bean("sqlSessionFactory")
//    public SqlSessionFactory sqlSessionFactory() throws Exception {
//        MybatisSqlSessionFactoryBean sqlSessionFactory = new MybatisSqlSessionFactoryBean();
//        sqlSessionFactory.setDataSource(multipleDataSource(aliYun(), paper()));
//        MybatisConfiguration configuration = new MybatisConfiguration();
//        configuration.setJdbcTypeForNull(JdbcType.NULL);
//        configuration.setMapUnderscoreToCamelCase(true);
//        configuration.setCacheEnabled(false);
//        sqlSessionFactory.setConfiguration(configuration);
//        sqlSessionFactory.setMapperLocations(applicationContext.getResources(mapperLocations));
//        sqlSessionFactory.setPlugins(new Interceptor[]{paginationInterceptor()});
//        return sqlSessionFactory.getObject();
//    }
//
//    @Bean("aliYun")
//    @ConfigurationProperties(prefix = "spring.datasource.druid.aliyun")
//    public DataSource aliYun() {
//        DataSourceBuilder.create().build();
//        return DruidDataSourceBuilder.create().build();
//    }
//
//    @Bean("paper")
//    @ConfigurationProperties(prefix = "spring.datasource.druid.paper")
//    public DataSource paper() {
//        return DruidDataSourceBuilder.create().build();
//    }
//
//    @Bean
//    @Primary
//    public DataSource multipleDataSource(@Qualifier("aliYun") DataSource aliYun, @Qualifier("paper") DataSource paper) {
//        AbstractRoutingDataSource abstractRoutingDataSource = new AbstractRoutingDataSource() {
//            @Override
//            protected Object determineCurrentLookupKey() {
//                return DbContextHolder.getDbType();
//            }
//        };
//        Map<Object, Object> targetDataSources = Maps.newHashMap();
//        targetDataSources.put(DBTypeEnum.ALI_YUN.getValue(), aliYun);
//        targetDataSources.put(DBTypeEnum.PAPER.getValue(), paper);
//        abstractRoutingDataSource.setTargetDataSources(targetDataSources);
//        //默认数据库
//        abstractRoutingDataSource.setDefaultTargetDataSource(aliYun);
//
//
//        return abstractRoutingDataSource;
//    }
//
//    @Override
//    public void setApplicationContext(@NotNull ApplicationContext applicationContext) throws BeansException {
//        this.applicationContext = applicationContext;
//    }
//
//}
