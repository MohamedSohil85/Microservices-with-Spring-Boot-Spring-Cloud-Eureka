package com.example.diagnose.diagnoseservice;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;

@SpringBootApplication
@EnableEurekaClient
public class DiagnoseserviceApplication {

    public static void main(String[] args) {
        SpringApplication.run(DiagnoseserviceApplication.class, args);
    }

}
