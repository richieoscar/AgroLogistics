package com.richieoscar.agrologistics.dto;

import com.richieoscar.agrologistics.domain.Route;
import lombok.Data;

import java.util.List;
@Data
public class LocationDTO {
    private Long id;

    private String name;

    private String longitude;

    private String latitude;

    private List<RouteDTO> routes;
}
