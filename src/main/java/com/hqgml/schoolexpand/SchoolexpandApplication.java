package com.hqgml.schoolexpand;

import com.dtflys.forest.springboot.annotation.ForestScan;
import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
@ForestScan(basePackages = "com.hqgml.schoolexpand.api")
@MapperScan("com.hqgml.schoolexpand.mapper")
public class SchoolexpandApplication {

    public static void main(String[] args) {
        SpringApplication.run(SchoolexpandApplication.class, args);
    }

}
