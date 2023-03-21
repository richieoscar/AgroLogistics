package com.richieoscar.agrologistics.service.impl;

import com.richieoscar.agrologistics.domain.Location;
import com.richieoscar.agrologistics.domain.Route;
import com.richieoscar.agrologistics.domain.Staff;
import com.richieoscar.agrologistics.dto.DefaultApiResponse;
import com.richieoscar.agrologistics.dto.LocationDTO;
import com.richieoscar.agrologistics.dto.RouteDTO;
import com.richieoscar.agrologistics.exception.LocationNotFoundException;
import com.richieoscar.agrologistics.exception.RouteNotAvailableException;
import com.richieoscar.agrologistics.mapper.LocationMapper;
import com.richieoscar.agrologistics.mapper.RouteMapper;
import com.richieoscar.agrologistics.repository.LocationRepository;
import com.richieoscar.agrologistics.repository.RouteRepository;
import com.richieoscar.agrologistics.service.LocationService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.util.Comparator;
import java.util.HashMap;
import java.util.List;
import java.util.Optional;

import static java.util.stream.Collectors.*;

@Slf4j
@Service
public class LocationServiceImpl implements LocationService {


    private final LocationRepository locationRepository;

    private final LocationMapper locationMapper;
    private final RouteMapper routeMapper;


    private final RouteRepository routeRepository;

    @Value("${price.cost}")
    private String costPerKilometer;


    @Autowired
    public LocationServiceImpl(LocationRepository locationRepository, LocationMapper locationMapper, RouteMapper routeMapper, RouteRepository routeRepository) {
        this.locationRepository = locationRepository;
        this.locationMapper = locationMapper;
        this.routeMapper = routeMapper;
        this.routeRepository = routeRepository;
    }

    @Override
    public DefaultApiResponse getLocations(int page, int size) {
        DefaultApiResponse defaultApiResponse = new DefaultApiResponse();
        PageRequest pageRequest = PageRequest.of(page, size, Sort.by(Sort.Direction.DESC, "name"));
        Page<Location> locations = locationRepository.findAll(pageRequest);
        HashMap<String, Object> map = new HashMap<>();
        map.put("totalPages", locations.getTotalPages());
        map.put("totalContents", locations.getTotalElements());
        log.info("{}", locations.getContent());
        map.put("items", locations.getContent());
        defaultApiResponse.setMessage("Locations Retrieved Successfully");
        defaultApiResponse.setStatus("success");
        defaultApiResponse.setData(map);
        return defaultApiResponse;
    }

    @Override
    public DefaultApiResponse updateLocation(Long id, LocationDTO locationDTO) {
        log.info("LocationServiceImpl::updateLocation");
        Location location = locationRepository.findById(id).orElseThrow(() -> new LocationNotFoundException("Location Not Found"));
        location.setLongitude(location.getLongitude());
        location.setLatitude(location.getLatitude());
        location.setName(location.getName());
        locationRepository.save(location);
        DefaultApiResponse defaultApiResponse = new DefaultApiResponse();
        defaultApiResponse.setStatus("success");
        defaultApiResponse.setMessage("Location Updated Successfully");
        log.info("Location Updated Successfully");
        return defaultApiResponse;
    }

    @Override
    public DefaultApiResponse deleteLocation(Long id) {
        log.info("LocationService::deleteLocation");
        Location location = locationRepository.findById(id).orElseThrow(() -> new LocationNotFoundException("Location Not Found"));
        locationRepository.delete(location);
        log.info("Location Deleted");
        DefaultApiResponse defaultApiResponse = new DefaultApiResponse();
        defaultApiResponse.setMessage("Location Deleted Successfully");
        defaultApiResponse.setStatus("success");
        return defaultApiResponse;
    }

    @Override
    public DefaultApiResponse addLocation(LocationDTO locationDTO) {
        log.info("LocationServiceImpl::addLocation");
        Location newLocation = locationMapper.mapToEntity(locationDTO);
        Location savedLocation = locationRepository.save(newLocation);
        if (locationDTO.getRoutes() != null && !locationDTO.getRoutes().isEmpty()) {
            List<Route> routes = locationDTO.getRoutes().stream()
                    .map(route -> {
                                Route route1 = routeMapper.mapToEntity(route);
                                route1.setLocation(savedLocation);
                                return route1;
                            }
                    ).collect(toList());
            routeRepository.saveAll(routes);
        }
        log.info("Location Added Successfully");
        DefaultApiResponse defaultApiResponse = new DefaultApiResponse();
        defaultApiResponse.setStatus("success");
        defaultApiResponse.setMessage("Location Added Successfully");
        return defaultApiResponse;
    }

    @Override
    public DefaultApiResponse getBestRoute(Long id, String fromLocation) {
        DefaultApiResponse defaultApiResponse = new DefaultApiResponse();
        log.info("LocationServiceImpl::getBestRoute");
        locationRepository.findById(id).orElseThrow(() -> new LocationNotFoundException("Location Not Found"));
        List<Route> routes = routeRepository.findAllByLocation_Id(id);
        List<RouteDTO> routeDtos = routes.stream().map(route -> routeMapper.mapToDto(route)).collect(toList());
        BigDecimal cost = BigDecimal.valueOf(Double.parseDouble(costPerKilometer));
        RouteDTO routeDTO = calculateBestRoute(routeDtos, cost, fromLocation);
        log.info("Best Route Calculated");
        defaultApiResponse.setMessage("Best Route Retrieved");
        defaultApiResponse.setStatus("success");
        defaultApiResponse.setData(routeDTO);
        return defaultApiResponse;
    }

    @Override
    public DefaultApiResponse getRoutes(Long locationId, int page, int size) {
        PageRequest pageRequest = PageRequest.of(page, size);
        Page<Route> routes = routeRepository.findAllByLocation_Id(locationId, pageRequest);
        routes.stream().map(route -> routeMapper.mapToDto(route)).collect(toList());
        HashMap<String, Object> map = new HashMap<>();
        map.put("totalPages", routes.getTotalPages());
        map.put("totalContents", routes.getTotalElements());
        map.put("items", routes.getContent());
        DefaultApiResponse defaultApiResponse = new DefaultApiResponse();
        defaultApiResponse.setData(map);
        defaultApiResponse.setStatus("success");
        defaultApiResponse.setMessage("Routes Retrieved Successfully");
        return defaultApiResponse;
    }

    @Override
    public DefaultApiResponse addRoute(Long locationId, RouteDTO routeDTO) {
        Location location = locationRepository.findById(locationId).orElseThrow(() -> new LocationNotFoundException("Location Not Found"));
        Route route = routeMapper.mapToEntity(routeDTO);
        route.setLocation(location);
        routeRepository.save(route);
        DefaultApiResponse defaultApiResponse = new DefaultApiResponse();
        defaultApiResponse.setStatus("success");
        defaultApiResponse.setMessage("Route Added Successfully");
        return defaultApiResponse;
    }

    @Override
    public DefaultApiResponse updateRoute(Long routeId, RouteDTO routeDTO) {
        Route route = routeRepository.findById(routeId).orElseThrow(() -> new RouteNotAvailableException("Route Not Found"));
        route.setTime(routeDTO.getTime());
        route.setDistance(routeDTO.getDistance());
        route.setPath(routeDTO.getPath());
        route.setTraffic(routeDTO.isTraffic());
        route.setName(routeDTO.getName());
        routeRepository.save(route);
        DefaultApiResponse defaultApiResponse = new DefaultApiResponse();
        defaultApiResponse.setMessage("Route Updated Successfully");
        defaultApiResponse.setStatus("success");
        return defaultApiResponse;
    }


    private RouteDTO calculateBestRoute(List<RouteDTO> routes, BigDecimal cost, String fromLocation) {
        log.info("Calculating Best Route");
        Optional<RouteDTO> bestRoute = routes.stream().
                filter(route -> route.getName().contains(fromLocation))
                .map(route -> {
                    BigDecimal costOfDelivery = BigDecimal.valueOf(route.getDistance()).multiply(cost);
                    route.setCostOfDelivery(costOfDelivery);
                    return route;
                })
                .filter(routeDTO -> !routeDTO.isTraffic())
                .min(Comparator.comparing(RouteDTO::getCostOfDelivery));
        return bestRoute.orElseThrow(() -> new RouteNotAvailableException("No Routes Available"));
    }

    public void loadLocations() {
        Location location = new Location();
        location.setName("IKORODU");
        location.setLongitude("9293873.09");
        location.setLatitude("111223.00");
        Location location1 = new Location();
        location1.setName("SANGOTEDO");
        location1.setLongitude("9293873.09");
        location1.setLatitude("111223.00");
        Location location2 = new Location();
        location2.setName("LEKKI");
        location2.setLongitude("9293873.09");
        location2.setLatitude("111223.00");
        Location location3 = new Location();
        location3.setName("FESTAC");
        location3.setLongitude("9293873.09");
        location3.setLatitude("111223.00");
        List<Location> initLocations = List.of(
                location3, location2, location1, location
        );
        locationRepository.saveAll(initLocations);
    }
}
