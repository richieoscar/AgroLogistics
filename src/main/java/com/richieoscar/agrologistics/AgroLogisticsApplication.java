package com.richieoscar.agrologistics;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import javax.annotation.PostConstruct;

@SpringBootApplication
public class AgroLogisticsApplication {

    @PostConstruct
    public void createSystemUser(){

    }

    public static void main(String[] args) {
        SpringApplication.run(AgroLogisticsApplication.class, args);
    }

}
