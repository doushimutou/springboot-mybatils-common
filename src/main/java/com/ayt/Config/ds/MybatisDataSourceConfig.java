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
import org.springframework.context.annotation.Primary;
import org.springframework.core.io.support.PathMatchingResourcePatternResolver;
import org.springframework.jdbc.datasource.DataSourceTransactionManager;

import javax.sql.DataSource;

/**
 * @author ayt  on 20180805
 */
@Configuration
//扫描 Mapper 接口并容器管理，包路径精确到 mapper
@MapperScan(basePackages = MybatisDataSourceConfig.PACKAGE,sqlSessionFactoryRef ="mybatisSqlSessionFactory" )
public class MybatisDataSourceConfig {
    //Mapper目录，以便跟其他数据源隔离
    static final String PACKAGE="com.ayt.mapper.city";
    static final  String MAPPER_LOCATION="classpath:/mapping/city/*.xml";
    //@Value 获取全局配置文件 application.properties 的 kv 配置,并自动装配
    @Value("${mybatis.datasource.url}")
    private String url;
    @Value("${mybatis.datasource.username}")
    private String user;
    @Value("${mybatis.datasource.password}")
    private String password;
    @Value("${mybatis.datasource.driverClassName}")
    private String driverClass;

    /**
     * @Primary 标志这个 Bean 如果在多个同类 Bean 候选时，该 Bean 优先被考虑。
     * 「多数据源配置的时候注意，必须要有一个主数据源，用 @Primary 标志该 Bean」
     * @return
     */
    @Bean(name="mybatisDataSource")
    @Primary
    public DataSource mybatisDataSource(){
        DruidDataSource dataSource=new DruidDataSource();
        dataSource.setDriverClassName(driverClass);
        dataSource.setUrl(url);
        dataSource.setUsername(user);
        dataSource.setPassword(password);
        return dataSource;
    }
    @Bean(name="mybatisTransactionManager")
    @Primary
    public DataSourceTransactionManager mybatisTransactionManager(){
        return new DataSourceTransactionManager(mybatisDataSource());
    }
    @Bean(name="mybatisSqlSessionFactory")
    @Primary
    public SqlSessionFactory mybatisSqlSessionFactory(@Qualifier("mybatisDataSource") DataSource mybatisDataSource) throws Exception {
        final SqlSessionFactoryBean sessionFactory=new SqlSessionFactoryBean();
        sessionFactory.setDataSource(mybatisDataSource);
        sessionFactory.setMapperLocations(new PathMatchingResourcePatternResolver().
                getResources(MybatisDataSourceConfig.MAPPER_LOCATION));
        return sessionFactory.getObject();
    }




}
