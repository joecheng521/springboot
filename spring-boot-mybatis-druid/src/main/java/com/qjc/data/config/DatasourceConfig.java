package com.qjc.data.config;

import java.sql.SQLException;
import java.util.HashMap;
import java.util.Map;

import javax.sql.DataSource;

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

import com.alibaba.druid.pool.DruidDataSource;
import com.qjc.enums.DataSourceEnum;

@Configuration
@MapperScan(basePackages = DatasourceConfig.PACKAGE, sqlSessionFactoryRef = "sqlSessionFactory")
public class DatasourceConfig {
	// 精确到 master 目录，以便跟其他数据源隔离
	static final String PACKAGE = "spring.datasource.druid";
	static final String MAPPER_LOCATION = "classpath:mapper/*.xml";
	@Value("${spring.datasource.druid.master.url}")
	private String masterUrl;

	@Value("${spring.datasource.druid.master.username}")
	private String masterUsername;

	@Value("${spring.datasource.druid.master.password}")
	private String masterPassword;

	@Value("${spring.datasource.druid.master.driver-class-name}")
	private String masterDriverClassName;

	@Value("${spring.datasource.druid.cluster.url}")
	private String clusterUrl;

	@Value("${spring.datasource.druid.cluster.username}")
	private String clusterUsername;

	@Value("${spring.datasource.druid.cluster.password}")
	private String clusterPassword;

	@Value("${spring.datasource.druid.cluster.driver-class-name}")
	private String clusterDriverClassName;

	@Value("${spring.datasource.initialSize}")
	private int initialSize;

	@Value("${spring.datasource.minIdle}")
	private int minIdle;

	@Value("${spring.datasource.maxActive}")
	private int maxActive;

	@Value("${spring.datasource.maxWait}")
	private int maxWait;

	@Value("${spring.datasource.timeBetweenEvictionRunsMillis}")
	private int timeBetweenEvictionRunsMillis;

	@Value("${spring.datasource.minEvictableIdleTimeMillis}")
	private int minEvictableIdleTimeMillis;

	@Value("${spring.datasource.validationQuery}")
	private String validationQuery;

	@Value("${spring.datasource.testWhileIdle}")
	private boolean testWhileIdle;

	@Value("${spring.datasource.testOnBorrow}")
	private boolean testOnBorrow;

	@Value("${spring.datasource.testOnReturn}")
	private boolean testOnReturn;

	@Value("${spring.datasource.poolPreparedStatements}")
	private boolean poolPreparedStatements;

	@Value("${spring.datasource.maxPoolPreparedStatementPerConnectionSize}")
	private int maxPoolPreparedStatementPerConnectionSize;

	@Value("${spring.datasource.filters}")
	private String filters;

	@Value("{spring.datasource.connectionProperties}")
	private String connectionProperties;
	
	
	@Bean(name = "dynamicDataSource")
    @Primary
    public DynamicDataSource dynamicDataSource() {
    	DynamicDataSource dynamicDataSource = DynamicDataSource.getInstance();
 
    	//jdbc配置
        DruidDataSource db1 = new DruidDataSource();
        db1.setDriverClassName(masterDriverClassName);
        db1.setUrl(masterUrl);
        db1.setUsername(masterUsername);
        db1.setPassword(masterPassword);
        
        //连接池配置
        db1.setMaxActive(maxActive);
        db1.setMinIdle(minIdle);
        db1.setInitialSize(initialSize);
        db1.setMaxWait(maxWait);
        db1.setTimeBetweenEvictionRunsMillis(timeBetweenEvictionRunsMillis);
        db1.setMinEvictableIdleTimeMillis(minEvictableIdleTimeMillis);
        db1.setTestWhileIdle(testWhileIdle);
        db1.setTestOnBorrow(testOnBorrow);
        db1.setTestOnReturn(testOnReturn);
        db1.setValidationQuery("SELECT 'x'");
        db1.setPoolPreparedStatements(true);
        db1.setMaxPoolPreparedStatementPerConnectionSize(20);
        try {
        	db1.setFilters("stat");
		} catch (SQLException e) {
			e.printStackTrace();
		}
        
        //jdbc配置
        DruidDataSource db2 = new DruidDataSource();
        db2.setDriverClassName(clusterDriverClassName);
        db2.setUrl(clusterUrl);
        db2.setUsername(clusterUsername);
        db2.setPassword(clusterPassword);
        
        //连接池配置
        db2.setMaxActive(maxActive);
        db2.setMinIdle(minIdle);
        db2.setInitialSize(initialSize);
        db2.setMaxWait(maxWait);
        db2.setTimeBetweenEvictionRunsMillis(timeBetweenEvictionRunsMillis);
        db2.setMinEvictableIdleTimeMillis(minEvictableIdleTimeMillis);
        db2.setTestWhileIdle(testWhileIdle);
        db2.setTestOnBorrow(testOnBorrow);
        db2.setTestOnReturn(testOnReturn);
        db2.setValidationQuery("SELECT 'x'");
        db2.setPoolPreparedStatements(true);
        db2.setMaxPoolPreparedStatementPerConnectionSize(20);
        try {
        	db2.setFilters("stat");
		} catch (SQLException e) {
			e.printStackTrace();
		}
        
        Map<Object,Object> map = new HashMap<>();
        map.put(DataSourceEnum.DB1.getValue(), db1);
        map.put(DataSourceEnum.DB2.getValue(), db2);
        
        dynamicDataSource.setTargetDataSources(map);
        dynamicDataSource.setDefaultTargetDataSource(db1);
        
        return dynamicDataSource;
    }
 
    @Bean(name = "transactionManager")
    @Primary
    public DataSourceTransactionManager transactionManager() {
        return new DataSourceTransactionManager(dynamicDataSource());
    }
 
    @Bean(name = "sqlSessionFactory")
    @Primary
    public SqlSessionFactory sqlSessionFactory(@Qualifier("dynamicDataSource") DataSource dynamicDataSource)
            throws Exception {
        final SqlSessionFactoryBean sessionFactory = new SqlSessionFactoryBean();
        sessionFactory.setDataSource(dynamicDataSource);
        sessionFactory.setTypeAliasesPackage("com.qjc.entry.*");
        sessionFactory.setMapperLocations(new PathMatchingResourcePatternResolver()
                .getResources(DatasourceConfig.MAPPER_LOCATION));
        return sessionFactory.getObject();
    }

}
