package com.example.inventorymanagementamazon.config;



import com.zaxxer.hikari.HikariDataSource;
import lombok.AllArgsConstructor;
import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import javax.sql.DataSource;

@Configuration
@AllArgsConstructor
@EnableConfigurationProperties(value = {PostgresConfig.LocalDataSource.class, PostgresConfig.PostgresConnectionPool.class})
public class PostgresConfig {

    @Data
    @ConfigurationProperties(prefix = "postgres.datasource")
    static class LocalDataSource{
        private String host;
        private String username;
        private String password;
        private String database;

        private String className;
    }
    @Data
    @ConfigurationProperties(prefix = "postgres.connection-pool")
    static class PostgresConnectionPool{
        private String name;
        private int minIdle;
        private int initialSize;
        private int maxSize;
        private long maxLifeTime;
        private long connectionTimeOut;
        private String validationQuery;
    }

    private LocalDataSource localDataSource;
    private PostgresConnectionPool connectionPool;

    @Bean
    public DataSource postgressDataSource (){
        final HikariDataSource hikariDataSource = new HikariDataSource();
        hikariDataSource.setDriverClassName(localDataSource.getClassName());
        hikariDataSource.setJdbcUrl(localDataSource.getHost());
        hikariDataSource.setUsername(localDataSource.getUsername());
        hikariDataSource.setPassword(localDataSource.getPassword());
        hikariDataSource.setPoolName(connectionPool.getName());
        hikariDataSource.setMinimumIdle(connectionPool.getMinIdle());
        hikariDataSource.setMaximumPoolSize(connectionPool.getMaxSize());
        hikariDataSource.setConnectionTimeout(connectionPool.getConnectionTimeOut());
        hikariDataSource.setMaxLifetime(connectionPool.getMaxLifeTime());
        hikariDataSource.setConnectionTestQuery(connectionPool.getValidationQuery());
        return hikariDataSource;
    }

}
