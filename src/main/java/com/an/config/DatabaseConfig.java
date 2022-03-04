package com.an.config;

import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

import java.util.List;

/**
 * TODO
 *
 * @author wuya
 * @date 2022/3/4 14:34
 */
@Data
@Component
@ConfigurationProperties(prefix = "database")
public class DatabaseConfig {

    private String oracle;


    private List<String> mysql;

}
