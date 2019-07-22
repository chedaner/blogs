package com.example.demo.configs;

import com.zaxxer.hikari.HikariDataSource;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.env.Environment;
import org.springframework.jdbc.datasource.DriverManagerDataSource;

import org.springframework.orm.hibernate5.HibernateTransactionManager;
import org.springframework.orm.hibernate5.LocalSessionFactoryBean;
import org.springframework.transaction.annotation.EnableTransactionManagement;

import javax.sql.DataSource;
import java.util.Properties;

/**
 * Created by ram on 02/03/16.
 */
@Configuration
@EnableTransactionManagement
public class DatabaseConfig {

    @Autowired
    Environment environment;

    @Bean(name = "dataSource")
    public DataSource dataSource(){

        HikariDataSource dataSource = new HikariDataSource();
        dataSource.setDriverClassName(environment.getProperty("db.driver"));
        dataSource.setJdbcUrl(environment.getProperty("db.url"));
        dataSource.setUsername(environment.getProperty("db.username"));
        dataSource.setPassword(environment.getProperty("db.password"));
        return dataSource;
    }

    @Bean
    public LocalSessionFactoryBean sessionFactory() {
        LocalSessionFactoryBean sessionFactoryBean = new LocalSessionFactoryBean();
        sessionFactoryBean.setDataSource(dataSource());
        sessionFactoryBean.setPackagesToScan(environment.getProperty("entitymanager.packagesToScan"));

        Properties hibernateProperties = new Properties();
        hibernateProperties.put("hibernate.dialect", environment.getProperty("hibernate.dialect"));
        hibernateProperties.put("hibernate.show_sql", environment.getProperty("hibernate.show_sql"));

        hibernateProperties.put("hibernate.Connection.useUnicode", environment.getProperty("hibernate.Connection.useUnicode"));
        hibernateProperties.put("hibernate.connection.characterEncoding", environment.getProperty("hibernate.connection.characterEncoding"));
        hibernateProperties.put("hibernate.connection.characterEncoding", environment.getProperty("hibernate.connection.characterEncoding"));
        hibernateProperties.put("hibernate.connection.pool_size", environment.getProperty("hibernate.connection.pool_size"));
        hibernateProperties.put("hibernate.jdbc.fetch_size", environment.getProperty("hibernate.jdbc.fetch_size"));
        hibernateProperties.put("hibernate.jdbc.batch_size", environment.getProperty("hibernate.jdbc.batch_size"));
        hibernateProperties.put("hibernate.connection.isolation", environment.getProperty("hibernate.connection.isolation"));
        hibernateProperties.put("hibernate.jdbc.use_get_generated_keys", environment.getProperty("hibernate.jdbc.use_get_generated_keys"));
        sessionFactoryBean.setHibernateProperties(hibernateProperties);
        return sessionFactoryBean;
    }

//    //分表需要
//    public Session getSession(String originTable, String targetTable) {
//        DaoAccessInterceptor interceptor = new DaoAccessInterceptor();//我们的拦截器
//        interceptor.setOriginTableName(originTable);//要拦截的目标表名,也就是需要分表的原始表名
//        interceptor.setTargetTableName(targetTable);  //要替换的子表名
//        Session session = sessionFactory.openSession(interceptor);//当前的session使用这个拦截器
//        return session;
//    }

    @Bean
    public HibernateTransactionManager transactionManager() {
        HibernateTransactionManager transactionManager =
                new HibernateTransactionManager();
        transactionManager.setSessionFactory(sessionFactory().getObject());
        return transactionManager;
    }


}
