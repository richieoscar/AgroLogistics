package com.richieoscar.agrologistics.mapper;

import com.richieoscar.agrologistics.domain.Staff;
import com.richieoscar.agrologistics.dto.StaffDTO;
import org.springframework.stereotype.Component;

@Component
public class StaffMapper implements Mapper<StaffDTO, Staff> {
    @Override
    public StaffDTO mapToDto(Staff source) {
        StaffDTO staffDTO = new StaffDTO();
        staffDTO.setId(source.getId());
        staffDTO.setEmail(source.getEmail());
        staffDTO.setRole(source.getRole());
        staffDTO.setLastName(source.getLastName());
        staffDTO.setFirstName(source.getFirstName());
        staffDTO.setRegisteredDate(source.getRegisteredDate());
        return staffDTO;
    }

    @Override
    public Staff mapToEntity(StaffDTO source) {
        return null;
    }
}
