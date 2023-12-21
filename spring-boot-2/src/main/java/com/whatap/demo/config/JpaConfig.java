package com.whatap.demo.config;

import javax.persistence.EntityManagerFactory;
import javax.sql.DataSource;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.boot.jdbc.DataSourceBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.orm.jpa.JpaTransactionManager;
import org.springframework.orm.jpa.LocalContainerEntityManagerFactoryBean;
import org.springframework.orm.jpa.vendor.HibernateJpaVendorAdapter;
import org.springframework.transaction.PlatformTransactionManager;

@Configuration
@EnableJpaRepositories(basePackages = "com.whatap.demo.jpa")
public class JpaConfig {

  @Bean
  public LocalContainerEntityManagerFactoryBean entityManagerFactory(DataSource dataSource) {
    HibernateJpaVendorAdapter vendorAdapter = new HibernateJpaVendorAdapter();
    vendorAdapter.setGenerateDdl(true); // Optional: for DDL generation
    vendorAdapter.setShowSql(true); // Optional: to show SQL queries
    vendorAdapter.setDatabasePlatform("org.hibernate.dialect.MySQL8Dialect"); // Set the dialect explicitly

    LocalContainerEntityManagerFactoryBean factoryBean = new LocalContainerEntityManagerFactoryBean();
    factoryBean.setDataSource(dataSource);
    factoryBean.setPackagesToScan("com.whatap.demo.jpa");
    factoryBean.setJpaVendorAdapter(vendorAdapter);
    return factoryBean;
  }

  @Bean
  public PlatformTransactionManager transactionManager(EntityManagerFactory entityManagerFactory) {
    return new JpaTransactionManager(entityManagerFactory);
  }

  @Bean
  @ConfigurationProperties(prefix = "spring.datasource")
  public DataSource dataSource() {
    DataSourceBuilder<?> dataSourceBuilder = DataSourceBuilder.create();
    dataSourceBuilder.url("jdbc:mysql://localhost:3310/api?useSSL=false&allowPublicKeyRetrieval=true&serverTimezone=Asia/Seoul&useLegacyDatetimeCode=false");
    dataSourceBuilder.username("root");
    dataSourceBuilder.password("1111");
    dataSourceBuilder.driverClassName("com.mysql.cj.jdbc.Driver");
    return dataSourceBuilder.build();
  }
}