package com.richieoscar.agrologistics.service;

import com.richieoscar.agrologistics.dto.DefaultApiResponse;
import com.richieoscar.agrologistics.dto.LocationDTO;
import com.richieoscar.agrologistics.dto.RouteDTO;

public interface LocationService {

    DefaultApiResponse getLocations(int page, int size);

    DefaultApiResponse updateLocation(Long id, LocationDTO locationDTO);

    DefaultApiResponse deleteLocation(Long id);

    DefaultApiResponse addLocation(LocationDTO locationDTO);
    DefaultApiResponse getBestRoute(Long id, String fromLocation);
    DefaultApiResponse getRoutes(Long locationId,int page , int size);
    DefaultApiResponse addRoute(Long locationId, RouteDTO routeDTO);
    DefaultApiResponse updateRoute(Long locationId, RouteDTO routeDTO);
    void loadLocations();




}
