package com.richieoscar.agrologistics;

import com.richieoscar.agrologistics.service.StaffService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import static org.junit.jupiter.api.Assertions.*;
@SpringBootTest
class AgroLogisticsApplicationTest {

    @Autowired
    private StaffService staffService;

    @Test
   void contextLoads(){
    }

}