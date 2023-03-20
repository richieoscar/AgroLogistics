package com.richieoscar.agrologistics.controllers;

import com.richieoscar.agrologistics.dto.DefaultApiResponse;
import com.richieoscar.agrologistics.service.StaffService;
import lombok.Getter;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@Slf4j
@RestController
@RequestMapping("api/v1/")
public class StaffController {

    @Autowired
    private StaffService staffService;

    @GetMapping
    public ResponseEntity<DefaultApiResponse> getStaffs(@RequestParam("page") int page, @RequestParam("size") int size) {
        log.info("StaffController::getStaffs");
        return ResponseEntity.ok(staffService.getStaffs(page, size));
    }

    @GetMapping
    public ResponseEntity<DefaultApiResponse> getStaff(@RequestParam("staffId") int staffId) {
        log.info("StaffController::getStaffs");
        return ResponseEntity.ok(staffService.getStaff(staffId));
    }
}
