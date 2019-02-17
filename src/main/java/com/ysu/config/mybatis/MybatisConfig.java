package com.ysu.config.mybatis;/**
 * Created by 韩建国 on 2019/2/17
 */

import com.alibaba.druid.pool.DruidDataSource;
import com.ysu.config.mybatis.datasource.DynamicDataSource;
import org.mybatis.spring.SqlSessionFactoryBean;
import org.mybatis.spring.annotation.MapperScan;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;
import org.springframework.core.io.Resource;
import org.springframework.core.io.support.PathMatchingResourcePatternResolver;

import javax.sql.DataSource;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;

/**
 * @Auther: han jianguo
 * @Date: 2019/2/17 22:26
 * @Description: 配置mybatis相关
 **/
@Configuration
@PropertySource("classpath:jdbc.properties")
@MapperScan(basePackages = {"com.ysu.db.dao"})
public class MybatisConfig {

    @Bean(name = "dataSource")
    @ConfigurationProperties(prefix = "spring.datasource")
    public DruidDataSource msgDataSource() throws Exception {
        return new DruidDataSource();
    }

    @Bean(name = "myDynamicDataSource")
    public DynamicDataSource myDynamicDataSource(
            @Qualifier("dataSource") DataSource msgDataSource) {
        Map<Object, Object> targetDataSources = new HashMap<>();
        targetDataSources.put("dataSource", msgDataSource);

        DynamicDataSource dataSource = new DynamicDataSource();
        dataSource.setTargetDataSources(targetDataSources);// 该方法是AbstractRoutingDataSource的方法
        dataSource.setDefaultTargetDataSource(msgDataSource);// 默认的datasource设置为msgDataSource
        return dataSource;

    }

    @Bean(name = "sqlSessionFactory")
    public SqlSessionFactoryBean sqlSessionFactory(@Qualifier("myDynamicDataSource") DynamicDataSource myDynamicDataSource) throws Exception {
        SqlSessionFactoryBean sqlSessionFactory = new SqlSessionFactoryBean();
        sqlSessionFactory.setDataSource(myDynamicDataSource);
        sqlSessionFactory.setTypeAliasesPackage("com.ysu.db.pojo");
        Resource[] resmsg = new PathMatchingResourcePatternResolver()
                .getResources("classpath:com/ysu/db/mapper/*Mapper.xml");
        ArrayList<Resource> resList = new ArrayList(Arrays.asList(resmsg));
        Resource[] res = new Resource[resList.size()];
        resList.toArray(res);
        sqlSessionFactory.setMapperLocations(res);
        return sqlSessionFactory;
    }

}
