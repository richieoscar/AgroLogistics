package com.richieoscar.agrologistics.controllers;

import com.richieoscar.agrologistics.dto.DefaultApiResponse;
import com.richieoscar.agrologistics.dto.LocationDTO;
import com.richieoscar.agrologistics.dto.RouteDTO;
import com.richieoscar.agrologistics.service.LocationService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.concurrent.CompletableFuture;

@Slf4j
@RestController
@RequestMapping("/api/v1/location")
public class LocationController {

    @Autowired
    private LocationService locationService;

    @GetMapping
    public ResponseEntity<DefaultApiResponse> getLocations(@RequestParam(value = "page", required = false) int page, @RequestParam(value = "size", required = false) int size) {
        log.info("LocationController::getLocations");
        DefaultApiResponse response = locationService.getLocations(page, size);
        return ResponseEntity.ok(response);
    }

    @PostMapping
    public ResponseEntity<DefaultApiResponse> addLocation(@RequestBody LocationDTO request) {
        log.info("LocationController::addLocation");
        DefaultApiResponse defaultApiResponse = locationService.addLocation(request);
        return ResponseEntity.ok(defaultApiResponse);
    }

    @GetMapping("/best-route")
    public CompletableFuture<ResponseEntity<DefaultApiResponse>> getBestRoute(@RequestParam("toLocation") Long id, @RequestParam("fromLocation") String fromLocation) {
        log.info("LocationController::getBestRoute {}", id, fromLocation);
        DefaultApiResponse bestRoute = locationService.getBestRoute(id, fromLocation);
        return CompletableFuture.completedFuture(ResponseEntity.ok(bestRoute));
    }

    @GetMapping("/routes")
    public CompletableFuture<ResponseEntity<DefaultApiResponse>> getRoutes(@RequestParam("locationId") Long id,
                                                                           @RequestParam("page") int page,
                                                                           @RequestParam("size") int size) {
        log.info("LocationController::getRoutes");
        DefaultApiResponse bestRoute = locationService.getRoutes(id, page, size);
        return CompletableFuture.completedFuture(ResponseEntity.ok(bestRoute));
    }

    @PostMapping ("/add-route/{locationId}")
    public CompletableFuture<ResponseEntity<DefaultApiResponse>> addRoute(@PathVariable("locationId") Long id, @RequestBody RouteDTO routeDTO) {
        log.info("LocationController::addRoute {}", id, routeDTO);
        DefaultApiResponse route = locationService.addRoute(id, routeDTO);
        return CompletableFuture.completedFuture(ResponseEntity.ok(route));
    }

    @PutMapping ("/update-route/{id}")
    public CompletableFuture<ResponseEntity<DefaultApiResponse>> updateRoute(@PathVariable("id") Long id, @RequestBody RouteDTO routeDTO) {
        log.info("LocationController::updateRoute {}", id, routeDTO);
        DefaultApiResponse route = locationService.updateRoute(id, routeDTO);
        return CompletableFuture.completedFuture(ResponseEntity.ok(route));
    }
}
