package com.richieoscar.agrologistics.controllers;

import com.richieoscar.agrologistics.dto.DefaultApiResponse;
import com.richieoscar.agrologistics.dto.RoleRequest;
import com.richieoscar.agrologistics.dto.StaffDTO;
import com.richieoscar.agrologistics.service.StaffService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.concurrent.CompletableFuture;

@Slf4j
@RestController
@RequestMapping("/api/v1/staff")
public class StaffController {


    @Autowired
    private StaffService staffService;

    @GetMapping("/all")
    public CompletableFuture<ResponseEntity<DefaultApiResponse>> getStaffs(@RequestParam("page") int page, @RequestParam("size") int size) {
        log.info("StaffController::getStaffs");
        return CompletableFuture.completedFuture(ResponseEntity.ok(staffService.getStaffs(page, size)));
    }

    @GetMapping
    public CompletableFuture<ResponseEntity<DefaultApiResponse>> getStaff(@RequestParam("staffId") int staffId) {
        log.info("StaffController::getStaffs");
        return CompletableFuture.completedFuture(ResponseEntity.ok(staffService.getStaff(staffId)));
    }

    @GetMapping("/add-role")
    public CompletableFuture<ResponseEntity<DefaultApiResponse>> addRole(@PathVariable("id") Long staffId, @RequestBody RoleRequest request) {
        log.info("StaffController::addRole");
        return CompletableFuture.completedFuture(ResponseEntity.ok(staffService.addRole(staffId, request.getRole())));
    }

    @PutMapping("/{id}")
    public CompletableFuture<ResponseEntity<DefaultApiResponse>> updateStaff(@PathVariable("id") Long staffId, @RequestBody StaffDTO staffDTO) {
        log.info("StaffController::getStaffs");
        return CompletableFuture.completedFuture(ResponseEntity.ok(staffService.updateStaff(staffId, staffDTO)));
    }

}
