package com.est.middleware.project.estuate.Wso2.config;

import java.util.Properties;

import javax.activation.DataSource;

import org.springframework.beans.factory.annotation.Configurable;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.jdbc.datasource.DriverManagerDataSource;
import org.springframework.orm.hibernate5.HibernateTransactionManager;
import org.springframework.orm.hibernate5.LocalSessionFactoryBean;
import org.springframework.transaction.annotation.EnableTransactionManagement;

@Configurable
@EnableTransactionManagement
public class DatabaseConfig {
	@Value("${db.driver}")
	private String DB_DRIVER;
	@Value("${db.password}")
	private String DB_PASSWORD;
	@Value("${db.url}")
	private String DB_URL;
	@Value("${db.username}")
	private String DB_USERNAME;
	@Value("${hibernate.dialect}")
	private String HIBERNATE_DIALECT;
	@Value("${hibernate.show_sql}")
	private String HIBERNATE_SHOW_SQL;
	@Value("${hibernate.hbm2ddl.auto}")
	private String HIBERNATE_HBM2DDL_AUTO;
	@Value("${entitymanager.packagesToScan}")
	private String ENTITYMANAGER_PACKAGES_TO_SCAN;
	@Value("${wso2.vm.ip}")
	public String WSO2_VM_IP_AND_PORT;
	@Value("${wso2.basic.auth.name}")
	public String WSO2_BASIC_AUTH_NAME;
	@Value("${wso2.basic.auth.password}")
	public String WSO2_BASIC_AUTH_PASSWORD;

	@Bean
	public DataSource dataSource() {
		final DriverManagerDataSource dataSource = new DriverManagerDataSource();
		dataSource.setDriverClassName(this.DB_DRIVER);
		dataSource.setUrl(this.DB_URL);
		dataSource.setUsername(this.DB_USERNAME);
		dataSource.setPassword(this.DB_PASSWORD);
		return (DataSource) dataSource;
	}

	@Bean
	public LocalSessionFactoryBean sessionFactory() {
		final LocalSessionFactoryBean sessionFactoryBean = new LocalSessionFactoryBean();
		sessionFactoryBean.setDataSource((javax.sql.DataSource) this.dataSource());
		sessionFactoryBean.setPackagesToScan(new String[] { this.ENTITYMANAGER_PACKAGES_TO_SCAN });
		final Properties hibernateProperties = new Properties();
		hibernateProperties.put("hibernate.dialect", this.HIBERNATE_DIALECT);
		hibernateProperties.put("hibernate.show_sql", this.HIBERNATE_SHOW_SQL);
		hibernateProperties.put("hibernate.hbm2ddl.auto", this.HIBERNATE_HBM2DDL_AUTO);
		sessionFactoryBean.setHibernateProperties(hibernateProperties);
		return sessionFactoryBean;
	}

	@Bean
	public HibernateTransactionManager transactionManager() {
		final HibernateTransactionManager transactionManager = new HibernateTransactionManager();
		transactionManager.setSessionFactory(this.sessionFactory().getObject());
		return transactionManager;
	}
}
