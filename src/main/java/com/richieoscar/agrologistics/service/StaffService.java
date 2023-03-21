package com.richieoscar.agrologistics.service;

import com.richieoscar.agrologistics.domain.Staff;
import com.richieoscar.agrologistics.dto.DefaultApiResponse;
import com.richieoscar.agrologistics.dto.StaffDTO;
import com.richieoscar.agrologistics.dto.auth.SignUpRequest;
import org.springframework.data.domain.Page;

public interface StaffService {

    StaffDTO saveStaff(SignUpRequest signUpRequest);

    Staff updateStaff(Long id, StaffDTO staffDTO);

    DefaultApiResponse getStaffs(int size, int page);
    DefaultApiResponse getStaff(int id);
}
