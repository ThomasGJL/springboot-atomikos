package com.example.springbootatomikos.config;

import com.alibaba.druid.filter.Filter;
import com.alibaba.druid.filter.logging.Slf4jLogFilter;
import com.alibaba.druid.filter.stat.StatFilter;
import com.alibaba.druid.pool.DruidDataSource;
import com.alibaba.druid.support.http.StatViewServlet;
import com.alibaba.druid.support.http.WebStatFilter;
import com.alibaba.druid.wall.WallConfig;
import com.alibaba.druid.wall.WallFilter;
import com.atomikos.icatch.jta.UserTransactionImp;
import com.atomikos.icatch.jta.UserTransactionManager;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.web.servlet.FilterRegistrationBean;
import org.springframework.boot.web.servlet.ServletRegistrationBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;
import org.springframework.core.env.Environment;
import org.springframework.transaction.jta.JtaTransactionManager;

import javax.transaction.UserTransaction;
import java.util.ArrayList;
import java.util.List;
import java.util.Properties;

/**
 * Druid配置
 */
@Configuration
public class DruidConfig {

    @Bean(name = "datasource1")
    @Primary
    @Autowired
    public DruidDataSource datasource1(Environment env) {
        DruidDataSource druidDataSource = new DruidDataSource();
        druidDataSource.setProxyFilters(this.filterList());

        Properties prop = build(env, "spring.datasource.druid.datasource1.");
        druidDataSource.setDriverClassName(prop.getProperty("driverClassName"));

        druidDataSource.setUrl(prop.getProperty("url"));
        druidDataSource.setUsername(prop.getProperty("username"));
        druidDataSource.setPassword(prop.getProperty("password"));
        druidDataSource.setInitialSize(Integer.parseInt(prop.getProperty("initialSize")));
        druidDataSource.setMinIdle(Integer.parseInt(prop.getProperty("minIdle")));
        druidDataSource.setMaxActive(Integer.parseInt(prop.getProperty("maxActive")));
        druidDataSource.setMaxWait(Long.parseLong(prop.getProperty("maxWait")));
        druidDataSource.setTimeBetweenEvictionRunsMillis(Long.parseLong(prop.getProperty("timeBetweenEvictionRunsMillis")));
        druidDataSource.setMinEvictableIdleTimeMillis(Long.parseLong(prop.getProperty("minEvictableIdleTimeMillis")));
        druidDataSource.setValidationQuery(prop.getProperty("validationQuery"));
        druidDataSource.setTestWhileIdle(Boolean.parseBoolean(prop.getProperty("testWhileIdle")));
        druidDataSource.setTestOnBorrow(Boolean.parseBoolean(prop.getProperty("testOnBorrow")));
        druidDataSource.setTestOnReturn(Boolean.parseBoolean(prop.getProperty("testOnReturn")));

        return druidDataSource;
    }

    @Bean(name = "datasource2")
    @Autowired
    public DruidDataSource datasource2(Environment env) {
        DruidDataSource druidDataSource = new DruidDataSource();
        druidDataSource.setProxyFilters(this.filterList());

        Properties prop = build(env, "spring.datasource.druid.datasource2.");
        druidDataSource.setDriverClassName(prop.getProperty("driverClassName"));
        druidDataSource.setUrl(prop.getProperty("url"));
        druidDataSource.setUsername(prop.getProperty("username"));
        druidDataSource.setPassword(prop.getProperty("password"));
        druidDataSource.setInitialSize(Integer.parseInt(prop.getProperty("initialSize")));
        druidDataSource.setMinIdle(Integer.parseInt(prop.getProperty("minIdle")));
        druidDataSource.setMaxActive(Integer.parseInt(prop.getProperty("maxActive")));
        druidDataSource.setMaxWait(Long.parseLong(prop.getProperty("maxWait")));
        druidDataSource.setTimeBetweenEvictionRunsMillis(Long.parseLong(prop.getProperty("timeBetweenEvictionRunsMillis")));
        druidDataSource.setMinEvictableIdleTimeMillis(Long.parseLong(prop.getProperty("minEvictableIdleTimeMillis")));
        druidDataSource.setValidationQuery(prop.getProperty("validationQuery"));
        druidDataSource.setTestWhileIdle(Boolean.parseBoolean(prop.getProperty("testWhileIdle")));
        druidDataSource.setTestOnBorrow(Boolean.parseBoolean(prop.getProperty("testOnBorrow")));
        druidDataSource.setTestOnReturn(Boolean.parseBoolean(prop.getProperty("testOnReturn")));

        return druidDataSource;
    }

    private List<Filter> filterList() {

        Slf4jLogFilter logFilter = new Slf4jLogFilter();
        logFilter.setConnectionLogEnabled(Boolean.FALSE);
        logFilter.setStatementLogEnabled(Boolean.TRUE);
        logFilter.setStatementExecutableSqlLogEnable(Boolean.TRUE);
        logFilter.setResultSetLogEnabled(Boolean.FALSE);

        List<Filter> filters = new ArrayList<>(1);
        filters.add(logFilter);

        return filters;
    }

    /**
     * 注入事物管理器
     *
     * @return
     */
    @Bean(name = "xatx")
    public JtaTransactionManager regTransactionManager() {
        UserTransactionManager userTransactionManager = new UserTransactionManager();
        UserTransaction userTransaction = new UserTransactionImp();
        return new JtaTransactionManager(userTransaction, userTransactionManager);
    }


    private Properties build(Environment env, String prefix) {

        Properties prop = new Properties();
        prop.put("url", env.getProperty(prefix + "url"));
        prop.put("username", env.getProperty(prefix + "username"));
        prop.put("password", env.getProperty(prefix + "password"));
        prop.put("driverClassName", env.getProperty(prefix + "driverClassName", ""));
        prop.put("initialSize", env.getProperty(prefix + "initialSize"));
        prop.put("maxActive", env.getProperty(prefix + "maxActive"));
        prop.put("minIdle", env.getProperty(prefix + "minIdle"));
        prop.put("maxWait", env.getProperty(prefix + "maxWait"));
        prop.put("poolPreparedStatements", env.getProperty(prefix + "poolPreparedStatements"));
        prop.put("maxPoolPreparedStatementPerConnectionSize",
                env.getProperty(prefix + "maxPoolPreparedStatementPerConnectionSize"));
        prop.put("maxPoolPreparedStatementPerConnectionSize",
                env.getProperty(prefix + "maxPoolPreparedStatementPerConnectionSize"));
        prop.put("validationQuery", env.getProperty(prefix + "validationQuery"));
        prop.put("validationQueryTimeout", env.getProperty(prefix + "validationQueryTimeout"));
        prop.put("testOnBorrow", env.getProperty(prefix + "testOnBorrow"));
        prop.put("testOnReturn", env.getProperty(prefix + "testOnReturn"));
        prop.put("testWhileIdle", env.getProperty(prefix + "testWhileIdle"));
        prop.put("timeBetweenEvictionRunsMillis",
                env.getProperty(prefix + "timeBetweenEvictionRunsMillis"));
        prop.put("minEvictableIdleTimeMillis", env.getProperty(prefix + "minEvictableIdleTimeMillis"));
        prop.put("filters", env.getProperty(prefix + "filters"));

        return prop;
    }


    @Bean
    public ServletRegistrationBean druidServlet() {
        ServletRegistrationBean servletRegistrationBean = new ServletRegistrationBean(new StatViewServlet(), "/druid/*");

        //控制台管理用户，加入下面2行 进入druid后台就需要登录
        servletRegistrationBean.addInitParameter("loginUsername", "admin");
        servletRegistrationBean.addInitParameter("loginPassword", "admin");
        return servletRegistrationBean;
    }

    @Bean
    public FilterRegistrationBean filterRegistrationBean() {
        FilterRegistrationBean filterRegistrationBean = new FilterRegistrationBean();
        filterRegistrationBean.setFilter(new WebStatFilter());
        filterRegistrationBean.addUrlPatterns("/*");
        filterRegistrationBean.addInitParameter("exclusions", "*.js,*.gif,*.jpg,*.png,*.css,*.ico,/druid/*");
        filterRegistrationBean.addInitParameter("profileEnable", "true");
        return filterRegistrationBean;
    }

    @Bean
    public StatFilter statFilter() {
        StatFilter statFilter = new StatFilter();
        statFilter.setLogSlowSql(true); //slowSqlMillis用来配置SQL慢的标准，执行时间超过slowSqlMillis的就是慢。
        statFilter.setMergeSql(true); //SQL合并配置
        statFilter.setSlowSqlMillis(1000);//slowSqlMillis的缺省值为3000，也就是3秒。
        return statFilter;
    }

    @Bean
    public WallFilter wallFilter() {
        WallFilter wallFilter = new WallFilter();
        //允许执行多条SQL
        WallConfig config = new WallConfig();
        config.setMultiStatementAllow(true);
        wallFilter.setConfig(config);
        return wallFilter;
    }
}
