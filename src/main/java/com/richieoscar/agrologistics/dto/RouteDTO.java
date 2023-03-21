package com.richieoscar.agrologistics.dto;

import com.richieoscar.agrologistics.domain.Location;
import lombok.Data;

import javax.persistence.*;
import java.math.BigDecimal;

@Data
public class RouteDTO {

    private Long id;

    private String name;

    private String path;

    private int distance;

    private  double time;

    private boolean isTraffic;
    private Location location;

    private BigDecimal costOfDelivery;
}
