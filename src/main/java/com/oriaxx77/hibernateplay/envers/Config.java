/**
 * 
 */
package com.oriaxx77.hibernateplay.envers;

import java.util.Properties;

import javax.sql.DataSource;

import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.config.BeanPostProcessor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.env.Environment;
import org.springframework.dao.annotation.PersistenceExceptionTranslationPostProcessor;
import org.springframework.jdbc.datasource.DriverManagerDataSource;
import org.springframework.orm.hibernate4.HibernateTransactionManager;
import org.springframework.orm.hibernate4.LocalSessionFactoryBean;
import org.springframework.transaction.PlatformTransactionManager;

/**
 * Spring configuration for the Hibernate Envers example app.
 * 
 */
@Configuration
public class Config
{
    /**
     * Environmental settings.
     */
    @Autowired
    private Environment env;
    
   
    
    /** Data source */
    @Bean
    public DataSource dataSource()
    {     
        DriverManagerDataSource ds = new DriverManagerDataSource();
        ds.setUrl( env.getProperty( "application.dbUrl" ) );
        ds.setDriverClassName( env.getProperty( "application.dbDriver" ) );
        ds.setUsername( "username" );
        ds.setPassword( "password" );
        return ds;    
        
    }
    
    /** HibernateSessionFactory */
    @Bean
    public LocalSessionFactoryBean sessionFactory( DataSource dataSource ) 
    {
        LocalSessionFactoryBean sfb = new LocalSessionFactoryBean();
        sfb.setDataSource( dataSource );
        sfb.setPackagesToScan("com.oriaxx77.hibernateplay.envers.model", "com.oriaxx77.hibernateplay.envers.custom");
        Properties props = new Properties();
        // Dialect
        props.setProperty( "hibernate.dialect", "org.hibernate.dialect.DerbyDialect");
        // Auto schema generation
        props.setProperty( "hibernate.hbm2ddl.auto", "create-drop" );
        // Logging
        props.setProperty( "hibernate.show_sql", "true" );
        props.setProperty( "hibernate.format_sql", "true" );
        props.setProperty( "hibernate.use_sql_comments", "true" );
        props.setProperty( "hibernate.generate_statistics", "true" );
        // Caching
        props.setProperty( "hibernate.cache.use_query_cache", "true" );
        props.setProperty( "hibernate.cache.region.factory_class", "org.hibernate.cache.ehcache.SingletonEhCacheRegionFactory" );
        props.setProperty( "hibernate.cache.use_second_level_cache", "true" );
        // Batch processing
        props.setProperty( "hibernate.jdbc.batch_size", "5" );
        props.setProperty( "hibernate.order_inserts", "true" );
        props.setProperty( "hibernate.order_updates", "true" );
        props.setProperty("hibernate.jdbc.batch_versioned_data", "true");
        // Envers
        props.setProperty(" org.hibernate.envers.audit_strategy", "org.hibernate.envers.strategy.DefaultAuditStrategy" );
        props.setProperty( "org.hibernate.envers.track_entities_changed_in_revision" , "true" );
        sfb.setHibernateProperties(props);
        return sfb;
    }

    /** TransactionManager */
    @Bean
    public PlatformTransactionManager transactionManager( SessionFactory sf ) 
    {
        HibernateTransactionManager txManager = new HibernateTransactionManager();
        txManager.setSessionFactory( sf );
        return txManager;
    }
    
    /** Bean for translating hibernate specific exceptions to spring data exceptions */
    @Bean
    public BeanPostProcessor persistenceTranslation() 
    {
        return new PersistenceExceptionTranslationPostProcessor();
    }
}
