package com.gc.java.spring.transaction;

import org.apache.commons.dbcp.BasicDataSource;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.orm.jpa.JpaTransactionManager;
import org.springframework.orm.jpa.LocalContainerEntityManagerFactoryBean;
import org.springframework.orm.jpa.vendor.HibernateJpaVendorAdapter;
import org.springframework.transaction.annotation.EnableTransactionManagement;

import javax.persistence.EntityManagerFactory;
import javax.sql.DataSource;
import java.util.Properties;

// resides in [spring-context]
@Configuration
// enable lookup for @Transactional methods
// resides in [spring-tx]
@EnableTransactionManagement
@ComponentScan(basePackages = "com.gc.java.spring")
public class Config {

    // datasource - for creation of connections
    @Bean
    public DataSource dataSource() {
        // DriverManagerDataSource - single connection for testing
        // BasicDataSource - poolable datasource from Apache - in [commons-dbcp]
        // ComboPooledDataSource - poolable datasource from c3p0
        // HikariDataSource - prod ready light datasource
        BasicDataSource dataSource = new BasicDataSource();
        dataSource.setDriverClassName("org.h2.Driver");
        dataSource.setUrl("jdbc:h2:mem:datajpa");
        dataSource.setUsername("sa");
        dataSource.setPassword("");
        return dataSource;
    }

    // To create EntityManagerFactory there are 3 ways:
    // - LocalEntityManagerFactoryBean - used in simple deployment environments such as stand-alone applications and integration tests
    // - Obtaining an EntityManagerFactory from JNDI - Use this option when deploying to a Java EE 5 server
    // - LocalContainerEntityManagerFactoryBean - full JPA capabilities in a Spring-based application environment.
    //                It supports links to an existing JDBC DataSource, supports both local and global transactions, and so on.
    //                However, it also imposes requirements on the runtime environment, such as the availability of a weaving-capable class loader if the persistence provider demands byte-code transformation.
    //
    // resides in [spring-orm]
    @Bean
    public LocalContainerEntityManagerFactoryBean entityManagerFactory(DataSource dataSource) {
        LocalContainerEntityManagerFactoryBean entityManagerFactoryBean = new LocalContainerEntityManagerFactoryBean();
        entityManagerFactoryBean.setDataSource(dataSource);
        entityManagerFactoryBean.setJpaVendorAdapter(new HibernateJpaVendorAdapter());
        entityManagerFactoryBean.setPackagesToScan("com.gc.java.spring.transaction");

        Properties jpaProperties = new Properties();
        //Configures the used database dialect. This allows Hibernate to create SQL
        //that is optimized for the used database.
        jpaProperties.put("hibernate.dialect", "org.hibernate.dialect.H2Dialect");
        //Specifies the action that is invoked to the database when the Hibernate
        //SessionFactory is created or closed.
        jpaProperties.put("hibernate.hbm2ddl.auto", "create-drop");
        //Configures the naming strategy that is used when Hibernate creates
        //new database objects and schema elements
        jpaProperties.put("hibernate.ejb.naming_strategy", "org.hibernate.cfg.ImprovedNamingStrategy");
        //If the value of this property is true, Hibernate writes all SQL
        //statements to the console.
        jpaProperties.put("hibernate.show_sql", "true");
        //If the value of this property is true, Hibernate will format the SQL
        //that is written to the console.
        jpaProperties.put("hibernate.format_sql", "true");

        entityManagerFactoryBean.setJpaProperties(jpaProperties);
        return entityManagerFactoryBean;
    }


    // PlatformTransactionManager - interface, integrates the JPA provider with the Spring transaction mechanism
    //  - DataSourceTransactionManager - synchronizes JDBC to transactions
    //  - HibernateTransactionManager  - synchronizes Hibernate SessionFactory to transactions
    //  - JpaTransactionManager - synchronizes JPA EntityManager to transactions - binds a JPA
    //            EntityManager from the specified factory to the thread. Is appropriate for applications that use a single
    //            JPA EntityManagerFactory for transactional data access
    // - JtaTransactionManager - is necessary for accessing multiple transactional resources within the same transaction
    // resides in [spring-orm]
    // EntityManagerFactory resides in [javax.persistence]
    @Bean
    public JpaTransactionManager transactionManager(EntityManagerFactory entityManagerFactory) {
        JpaTransactionManager transactionManager = new JpaTransactionManager();
        transactionManager.setEntityManagerFactory(entityManagerFactory);
        return transactionManager;
    }

}
