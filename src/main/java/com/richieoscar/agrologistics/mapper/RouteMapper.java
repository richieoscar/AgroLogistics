package com.richieoscar.agrologistics.mapper;

import com.richieoscar.agrologistics.domain.Location;
import com.richieoscar.agrologistics.dto.LocationDTO;
import org.springframework.stereotype.Component;

@Component
public class LocationMapper implements Mapper<LocationDTO, Location> {
    @Override
    public LocationDTO mapToDto(Location source) {
        LocationDTO locationDTO = new LocationDTO();
        locationDTO.setName(source.getName());
        locationDTO.setLatitude(source.getLatitude());
        locationDTO.setLongitude(source.getLongitude());
        return locationDTO;
    }

    @Override
    public Location mapToEntity(LocationDTO source) {
        Location locationEntity = new Location();
        locationEntity.setName(source.getName());
        locationEntity.setLatitude(source.getLatitude());
        locationEntity.setLongitude(source.getLongitude());
        return locationEntity;
    }
}
