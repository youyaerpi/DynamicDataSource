package com.ecjtu.dynamicdatasource.config;

import com.alibaba.druid.pool.DruidDataSource;
import com.baomidou.mybatisplus.core.MybatisConfiguration;
import com.baomidou.mybatisplus.extension.plugins.PaginationInterceptor;
import com.baomidou.mybatisplus.extension.spring.MybatisSqlSessionFactoryBean;
import com.ecjtu.dynamicdatasource.config.datasource.DBTypeEnum;
import com.ecjtu.dynamicdatasource.config.datasource.DbContextHolder;
import org.apache.ibatis.plugin.Interceptor;
import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.ibatis.type.JdbcType;
import org.mybatis.spring.annotation.MapperScan;
import org.springframework.beans.BeansException;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;
import org.springframework.jdbc.datasource.lookup.AbstractRoutingDataSource;

import javax.sql.DataSource;
import java.util.HashMap;
import java.util.Map;

/**
 * mybatis-plus配置类
 *
 * @author xiexiang
 * @date 2019/10/25 2:14 下午
 */
@Configuration
@MapperScan("com.ecjtu.dynamicdatasource.mapper*")
public class MybatisPlusConfig implements ApplicationContextAware {

    @Value("${spring.datasource.druid.driverClassName}")
    private String driverClassName;
    @Value("${spring.datasource.druid.initialSize}")
    private Integer initialSize;
    @Value("${spring.datasource.druid.maxActive}")
    private Integer maxActive;
    @Value("${spring.datasource.druid.testOnBorrow}")
    private Boolean testOnBorrow;
    @Value("${spring.datasource.druid.validationQuery}")
    private String validationQuery;

    @Value("${spring.datasource.aliYun.username}")
    private String aliYunUserName;
    @Value("${spring.datasource.aliYun.url}")
    private String aliYunUrl;
    @Value("${spring.datasource.aliYun.password}")
    private String aliYunPassword;
    @Value("${spring.datasource.paper.username}")
    private String paperUserName;
    @Value("${spring.datasource.paper.url}")
    private String paperUrl;
    @Value("${spring.datasource.paper.password}")
    private String paperPassword;


    private ApplicationContext applicationContext;

    @Value("${mybatis-plus.config-location}")
    private String mapperLocations;



    /**
     * mybatis-plus分页插件<br>
     * 文档：http://mp.baomidou.com<br>
     */
    @Bean
    public PaginationInterceptor paginationInterceptor() {
        PaginationInterceptor paginationInterceptor = new PaginationInterceptor();
        paginationInterceptor.setLimit(5000);
        paginationInterceptor.setDialectType("mysql");
        return paginationInterceptor;
    }

    /**
     * 配置mybatis的sqlSessionFactory
     * @return     {@link SqlSessionFactory}
     * @throws      {@link Exception}
     */
    @Bean("sqlSessionFactory")
    public SqlSessionFactory sqlSessionFactory() throws Exception {
        MybatisSqlSessionFactoryBean sqlSessionFactory = new MybatisSqlSessionFactoryBean();
        sqlSessionFactory.setDataSource(multipleDataSource(aliYun(),paper()));
        MybatisConfiguration configuration = new MybatisConfiguration();
        configuration.setJdbcTypeForNull(JdbcType.NULL);
        configuration.setMapUnderscoreToCamelCase(true);
        configuration.setCacheEnabled(false);
        sqlSessionFactory.setConfiguration(configuration);
        sqlSessionFactory.setMapperLocations(applicationContext.getResources(mapperLocations));
        sqlSessionFactory.setPlugins(new Interceptor[]{
                paginationInterceptor()
        });
        return sqlSessionFactory.getObject();
    }

    @Bean("aliYun")
    public DataSource aliYun() {
        DruidDataSource druidDataSource = initDruidDataSource();
        druidDataSource.setUrl(aliYunUrl);
        druidDataSource.setUsername(aliYunUserName);
        druidDataSource.setPassword(aliYunPassword);
        return druidDataSource;
    }

    @Bean("paper")
    public DataSource paper() {
        DruidDataSource druidDataSource = initDruidDataSource();
        druidDataSource.setUrl(paperUrl);
        druidDataSource.setUsername(paperUserName);
        druidDataSource.setPassword(paperPassword);
        return druidDataSource;
    }
    @Bean
    @Primary
    public DataSource multipleDataSource(@Qualifier("aliYun")DataSource aliYun,
                                         @Qualifier("paper")DataSource  paper){
        AbstractRoutingDataSource abstractRoutingDataSource = new AbstractRoutingDataSource() {
            @Override
            protected Object determineCurrentLookupKey() {
                return DbContextHolder.getDbType();
            }
        };
        Map<Object, Object> targetDataSources = new HashMap<>(2);
        targetDataSources.put(DBTypeEnum.ALI_YUN.getValue(),aliYun);
        targetDataSources.put(DBTypeEnum.PAPER.getValue(),paper);
        abstractRoutingDataSource.setTargetDataSources(targetDataSources);
        //默认数据库
        abstractRoutingDataSource.setDefaultTargetDataSource(aliYun);


        return abstractRoutingDataSource;
    }


    /**
     * 初始化数据源
     * @return
     */
    private DruidDataSource initDruidDataSource() {
        DruidDataSource druidDataSource = new DruidDataSource();
        druidDataSource.setDriverClassName(driverClassName);
        druidDataSource.setInitialSize(initialSize);
        druidDataSource.setMaxActive(maxActive);
        druidDataSource.setTestOnBorrow(testOnBorrow);
        druidDataSource.setValidationQuery(validationQuery);
        return druidDataSource;
    }


    @Override
    public void setApplicationContext(ApplicationContext applicationContext) throws BeansException {
        this.applicationContext = applicationContext;
    }

}
