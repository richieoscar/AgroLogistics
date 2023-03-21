package com.richieoscar.agrologistics;

import com.richieoscar.agrologistics.service.StaffService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.scheduling.annotation.Async;

import javax.annotation.PostConstruct;

@SpringBootApplication
public class AgroLogisticsApplication {

//    @Autowired
//    private StaffService staffService;
//    @Async
//    @PostConstruct
//    public void createSystemUser() {
//        staffService.createSystemUser();
//    }

    public static void main(String[] args) {
        SpringApplication.run(AgroLogisticsApplication.class, args);
    }

}
