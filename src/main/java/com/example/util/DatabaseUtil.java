package com.example.util;

import com.zaxxer.hikari.HikariConfig;
import com.zaxxer.hikari.HikariDataSource;

import javax.sql.DataSource;
import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

public class DatabaseUtil {
    private static HikariDataSource dataSource;

    static{
            try(InputStream input = DatabaseUtil.class.getClassLoader().getResourceAsStream("application.properties"))
                {
                    Properties prop = new Properties();
                    if(input == null){
                        throw new RuntimeException("Unable to find application.properties");
                    }
                    prop.load(input);

                    HikariConfig config = new HikariConfig();
                    config.setJdbcUrl(prop.getProperty("db.url"));
                    config.setUsername(prop.getProperty("db.username"));
                    config.setPassword(prop.getProperty("db.password"));

                    config.setMaximumPoolSize(Integer.parseInt(prop.getProperty("hikari.maximumPoolSize", "10")));
                    config.setMinimumIdle(Integer.parseInt(prop.getProperty("hikari.minimumIdle", "2")));
                    config.setIdleTimeout(Long.parseLong(prop.getProperty("hikari.idleTimeout", "30000")));

                    dataSource = new HikariDataSource(config);
                }catch (IOException e){
                throw new RuntimeException("Failed to load application.properties",e);
            }
    }
    public static DataSource getDataSource(){
        return dataSource;
    }
}
