package com.manageservice.users.dao;

import com.zaxxer.hikari.HikariConfig;
import com.zaxxer.hikari.HikariDataSource;
import lombok.extern.slf4j.Slf4j;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Profile;
import org.springframework.core.env.Environment;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.jdbc.core.namedparam.SqlParameterSource;
import org.springframework.stereotype.Component;
import org.springframework.transaction.PlatformTransactionManager;
import org.springframework.transaction.TransactionDefinition;
import org.springframework.transaction.TransactionException;
import org.springframework.transaction.TransactionStatus;
import org.springframework.transaction.support.SimpleTransactionStatus;

import javax.sql.DataSource;

@Slf4j
public class BasicDao {

    protected DataSource dataSource;

    protected NamedParameterJdbcTemplate namedParameterJdbcTemplate;

    @Autowired
    private Environment env;

    public void initializeDataSource() {

        HikariConfig jdbcConfig = new HikariConfig();
        jdbcConfig.setUsername(env.getProperty("spring.datasource.username"));
        jdbcConfig.setPassword(env.getProperty("spring.datasource.password"));
        jdbcConfig.setJdbcUrl(env.getProperty("spring.datasource.url"));
        jdbcConfig.setPoolName(env.getProperty("spring.datasource.poolname"));
        jdbcConfig.setConnectionTimeout(Integer.valueOf(env.getProperty("spring.datasource.hikari.connection-timeout")));
        jdbcConfig.setIdleTimeout(Integer.valueOf(env.getProperty("spring.datasource.hikari.idle-timeout")));
        jdbcConfig.setMaximumPoolSize(Integer.valueOf(env.getProperty("spring.datasource.hikari.maximum-pool-size")));
        jdbcConfig.setMinimumIdle(Integer.valueOf(env.getProperty("spring.datasource.hikari.minimum-idle")));
        jdbcConfig.setDriverClassName(env.getProperty("spring.datasource.driver-class-name"));
        dataSource = new HikariDataSource(jdbcConfig);
        namedParameterJdbcTemplate = new NamedParameterJdbcTemplate(dataSource);
        log.info("Called InitializationDataSource");
    }

    @Bean
    public DataSource dataSource(){
       if(dataSource == null){
            initializeDataSource();
        }
        return dataSource;
    }

    @Bean
    public PlatformTransactionManager txManager(){
        return new PlatformTransactionManager() {
            @Override
            public TransactionStatus getTransaction(TransactionDefinition definition) throws TransactionException {
                return new SimpleTransactionStatus();
            }

            @Override
            public void commit(TransactionStatus status) throws TransactionException {
              log.warn("commit invoked ",status);
            }

            @Override
            public void rollback(TransactionStatus status) throws TransactionException {
              log.warn("rollback invoked", status);
            }
        };
    }

    public <T> T findObject(String query, SqlParameterSource namedParameters, Class<T> requiredType){
        return namedParameterJdbcTemplate.queryForObject(query, namedParameters, requiredType);
    }
}
