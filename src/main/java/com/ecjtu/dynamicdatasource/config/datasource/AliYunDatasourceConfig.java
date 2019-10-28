package com.ecjtu.dynamicdatasource.config.datasource;

import com.alibaba.druid.spring.boot.autoconfigure.DruidDataSourceBuilder;
import com.baomidou.mybatisplus.core.MybatisConfiguration;
import com.baomidou.mybatisplus.extension.spring.MybatisSqlSessionFactoryBean;
import com.ecjtu.dynamicdatasource.util.SpringContextUtil;
import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.ibatis.type.JdbcType;
import org.mybatis.spring.SqlSessionTemplate;
import org.mybatis.spring.annotation.MapperScan;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;

import javax.sql.DataSource;



/**
 * @author xiexiang
 * @date 2019/10/28 1:59 下午
 */
@Configuration
@MapperScan(basePackages = "com.ecjtu.dynamicdatasource.mapper.aliyun.*", sqlSessionFactoryRef = "aliYunSqlSessionFactory")
public class AliYunDatasourceConfig {

    @Value("${mybatis-plus.config-location}")
    public  String mapperLocations;

    @Bean("aliYun")
    @ConfigurationProperties(prefix = "spring.datasource.druid.aliyun")
    public DataSource aliYun() {
        return DruidDataSourceBuilder.create().build();
    }

    @Bean("aliYunSqlSessionFactory")
    public SqlSessionFactory sqlSessionFactory(@Qualifier("aliYun") DataSource dataSource) throws Exception {
        MybatisSqlSessionFactoryBean sqlSessionFactory = new MybatisSqlSessionFactoryBean();
        sqlSessionFactory.setDataSource(dataSource);
        MybatisConfiguration configuration = new MybatisConfiguration();
        configuration.setJdbcTypeForNull(JdbcType.NULL);
        configuration.setMapUnderscoreToCamelCase(true);
        configuration.setCacheEnabled(false);
        sqlSessionFactory.setConfiguration(configuration);
        sqlSessionFactory.setMapperLocations(SpringContextUtil.APPLICATION_CONTEXT.getResources(mapperLocations));
        return sqlSessionFactory.getObject();
    }

//    @Bean(name = "aliYunSqlSessionTemplate")
//    @Primary
//    public SqlSessionTemplate testSqlSessionTemplate(@Qualifier("aliYunSqlSessionFactory") SqlSessionFactory sqlSessionFactory) {
//        return new SqlSessionTemplate(sqlSessionFactory);
//    }


}
