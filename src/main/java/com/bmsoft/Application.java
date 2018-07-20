package com.bmsoft;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.transaction.annotation.EnableTransactionManagement;
import com.bmsoft.common.config.BmsoftProperies;

@SpringBootApplication
@EnableTransactionManagement
@MapperScan("com.bmsoft.*.dao")
@EnableConfigurationProperties({BmsoftProperies.class})
public class Application {

    public static void main(String[] args) {
        SpringApplication.run(Application.class, args);
        System.out.println("complete");
    }
}
