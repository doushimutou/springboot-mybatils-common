package com.ayt.Config.ds;/**
 * @Title: ${file_name}
 * @Package ${package_name}
 * @Description: ${todo}
 * @author ayt
 * @date 2018/8/513:53
 */

import com.alibaba.druid.pool.DruidDataSource;
import org.apache.ibatis.session.SqlSessionFactory;
import org.mybatis.spring.SqlSessionFactoryBean;
import org.mybatis.spring.annotation.MapperScan;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.io.support.PathMatchingResourcePatternResolver;
import org.springframework.jdbc.datasource.DataSourceTransactionManager;

import javax.sql.DataSource;

/**
 * @author ayt  on 20180805
 */
@Configuration
@MapperScan(basePackages =MytestDataSourceConfig.PACKAGE,sqlSessionFactoryRef = "mytestSqlSessionFactory")
public class MytestDataSourceConfig {

    static final String PACKAGE="com.ayt.mapper";
    static final String MAPPER_LOCATION="classpath:/mapping/*.xml";
    //@Value 获取全局配置文件 application.properties 的 kv 配置,并自动装配
    @Value("${mytest.datasource.url}")
    private String url;
    @Value("${mytest.datasource.username}")
    private String user;
    @Value("${mytest.datasource.password}")
    private String password;
    @Value("${mytest.datasource.driverClassName}")
    private String driverClass;



    @Bean(name="mytestDataSource")
    public DataSource mytestDataSource(){
        DruidDataSource dataSource=new DruidDataSource();
        dataSource.setDriverClassName(driverClass);
        dataSource.setUrl(url);
        dataSource.setUsername(user);
        dataSource.setPassword(password);
        return dataSource;
    }
    @Bean(name="mytestTransactionManager")
    public DataSourceTransactionManager mytestTransactionManager(){
        return new DataSourceTransactionManager(mytestDataSource());
    }


    @Bean(name="mytestSqlSessionFactory")
    public SqlSessionFactory mytestSqlSessionFactory (@Qualifier("mytestDataSource") DataSource mytestDataSource ) throws Exception {
        final SqlSessionFactoryBean sessionFactory=new SqlSessionFactoryBean();
        sessionFactory.setDataSource(mytestDataSource);
        sessionFactory.setMapperLocations(new PathMatchingResourcePatternResolver().
                getResources(MytestDataSourceConfig.MAPPER_LOCATION));
        return sessionFactory.getObject();


    }












}
