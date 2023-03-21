package com.richieoscar.agrologistics.service.impl;

import com.richieoscar.agrologistics.domain.Location;
import com.richieoscar.agrologistics.domain.Staff;
import com.richieoscar.agrologistics.dto.DefaultApiResponse;
import com.richieoscar.agrologistics.dto.StaffDTO;
import com.richieoscar.agrologistics.dto.auth.SignUpRequest;
import com.richieoscar.agrologistics.enumeration.Role;
import com.richieoscar.agrologistics.exception.StaffException;
import com.richieoscar.agrologistics.mapper.StaffMapper;
import com.richieoscar.agrologistics.repository.StaffRepository;
import com.richieoscar.agrologistics.service.StaffService;
import com.richieoscar.agrologistics.util.PasswordUtil;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.HashMap;
import java.util.List;
import java.util.Objects;
import java.util.Optional;
import java.util.stream.Collectors;

@Slf4j
@Service
public class StaffServiceImpl implements StaffService {

    @Autowired
    private StaffRepository staffRepository;
    @Autowired
    private PasswordUtil passwordUtil;

    @Autowired
    private StaffMapper staffMapper;

    @Override
    public StaffDTO saveStaff(SignUpRequest signUpRequest) {
        log.info("StaffService::saveStaff {}", signUpRequest);
        Optional<Staff> staff = staffRepository.findByEmail(signUpRequest.getEmail());
        if (staff.isPresent()) throw new StaffException("Email in use");
        Staff newStaff = new Staff();
        newStaff.setEmail(signUpRequest.getEmail());
        newStaff.setFirstName(signUpRequest.getFirstName());
        newStaff.setLastName(signUpRequest.getLastName());
        newStaff.setPassword(passwordUtil.encodePassword(signUpRequest.getPassword()));
        newStaff.setRegisteredDate(LocalDateTime.now());
        newStaff.setRole(Role.USER);
        Staff savedStaff = staffRepository.save(newStaff);
        StaffDTO staffDTO = new StaffDTO();
        BeanUtils.copyProperties(savedStaff, staffDTO);
        log.info("Staff Saved Successfully");
        return staffDTO;
    }

    @Override
    public DefaultApiResponse updateStaff(Long id, StaffDTO staffDTO) {
        log.info("StaffServiceImpl:updateStaff");
        Staff staff = staffRepository.findById( id).orElseThrow(() -> new StaffException("Staff Not Found"));
        staff.setLastName(staffDTO.getLastName());
        staff.setEmail(staffDTO.getEmail());
        staff.setFirstName(staffDTO.getFirstName());
        staffRepository.save(staff);
        DefaultApiResponse defaultApiResponse = new DefaultApiResponse();
        defaultApiResponse.setMessage("Staff Updated Successful");
        defaultApiResponse.setStatus("success");
        return defaultApiResponse;
    }

    @Override
    public DefaultApiResponse getStaffs(int size, int page) {
        log.info("StaffServiceImpl::getStaffs");
        DefaultApiResponse defaultApiResponse = new DefaultApiResponse();
        PageRequest pageRequest = PageRequest.of(size, page, Sort.by(Sort.Direction.DESC, "registeredDate"));
        Page<Staff> stafs = staffRepository.findAll(pageRequest);
        HashMap<String, Object> map = new HashMap<>();
        map.put("totalPages", stafs.getTotalPages());
        map.put("totalContents", stafs.getTotalElements());
        List<StaffDTO> staffDTOS = stafs.getContent().stream().map(staff -> staffMapper.mapToDto(staff)).collect(Collectors.toList());
        map.put("items", staffDTOS);
        defaultApiResponse.setMessage("Staffs Retrieved Successfully");
        defaultApiResponse.setStatus("success");
        defaultApiResponse.setData(map);
        return defaultApiResponse;
    }

    @Override
    public DefaultApiResponse getStaff(int id) {
        log.info("StaffServiceImpl::getStaff");
        Staff staff = staffRepository.findById((long) id).orElseThrow(() -> new StaffException("Staff Not Found"));
        StaffDTO staffDto = new StaffDTO();
        BeanUtils.copyProperties(staff, staffDto);
        DefaultApiResponse defaultApiResponse = new DefaultApiResponse();
        defaultApiResponse.setStatus("success");
        defaultApiResponse.setMessage("Staff Retrieved Successfully");
        defaultApiResponse.setData(staffDto);
        return defaultApiResponse;
    }

    @Override
    public DefaultApiResponse addRole(Long id, String role) {
        log.info("StaffServiceImpl::addRole");
        Staff staff = staffRepository.findById(id).orElseThrow(() -> new StaffException("Staff Not Found"));
        staff.setRole(Role.valueOf(role));
        staffRepository.save(staff);
        log.info("Role Added Successful");
        DefaultApiResponse defaultApiResponse = new DefaultApiResponse();
        defaultApiResponse.setStatus("success");
        defaultApiResponse.setMessage("Role Added Successfully");
        return defaultApiResponse;
    }
}
