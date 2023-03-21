package com.richieoscar.agrologistics.mapper;

import com.richieoscar.agrologistics.domain.Location;
import com.richieoscar.agrologistics.domain.Route;
import com.richieoscar.agrologistics.dto.LocationDTO;
import com.richieoscar.agrologistics.dto.RouteDTO;
import org.springframework.stereotype.Component;

@Component
public class RouteMapper implements Mapper<RouteDTO, Route> {

    @Override
    public RouteDTO mapToDto(Route source) {
        RouteDTO routeDTO = new RouteDTO();
        routeDTO.setDistance(source.getDistance());
        routeDTO.setName(source.getName());
        routeDTO.setLocation(source.getLocation());
        routeDTO.setPath(source.getPath());
        routeDTO.setName(source.getName());
        routeDTO.setTraffic(source.isTraffic());
        routeDTO.setTime(source.getTime());
        return routeDTO;
    }

    @Override
    public Route mapToEntity(RouteDTO source) {
        Route route = new Route();
        route.setDistance(source.getDistance());
        route.setName(source.getName());
        route.setLocation(source.getLocation());
        route.setPath(source.getPath());
        route.setName(source.getName());
        route.setTraffic(source.isTraffic());
        route.setTime(source.getTime());
        return route;
    }
}
