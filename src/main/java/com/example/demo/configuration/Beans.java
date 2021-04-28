package com.example.demo.configuration;

import com.orientechnologies.orient.core.config.OGlobalConfiguration;
import com.orientechnologies.orient.core.db.ODatabasePool;
import com.orientechnologies.orient.core.db.OrientDB;
import com.orientechnologies.orient.core.db.OrientDBConfig;
import com.orientechnologies.orient.core.db.OrientDBConfigBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class Beans {

    @Bean
    public OrientDB orientDB() {
        return new OrientDB("remote:localhost", OrientDBConfig.defaultConfig());
    }

    @Bean
    public OrientDBConfigBuilder orientDBConfigBuilder() {
        return OrientDBConfig.builder().addConfig(OGlobalConfiguration.DB_POOL_MIN, 5).addConfig(OGlobalConfiguration.DB_POOL_MAX, 10);
    }

    @Bean
    public ODatabasePool oDatabasePool() {
        return new ODatabasePool(orientDB(), "graphDbDemo", "root", "hello", orientDBConfigBuilder().build());
    }
}