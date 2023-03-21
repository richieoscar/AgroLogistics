package com.richieoscar.agrologistics;

import com.richieoscar.agrologistics.service.StaffService;
import com.richieoscar.agrologistics.service.impl.StaffServiceImpl;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.scheduling.annotation.Async;

import javax.annotation.PostConstruct;

@SpringBootApplication

public class AgroLogisticsApplication {


    private final StaffService staffService;

    @Autowired
    public AgroLogisticsApplication(StaffService staffService) {
        this.staffService = staffService;
    }


    @PostConstruct
    public void createSystemUser() {
        staffService.createSystemUser();
    }

    public static void main(String[] args) {
        SpringApplication.run(AgroLogisticsApplication.class, args);
    }

}
