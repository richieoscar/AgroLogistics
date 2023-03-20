package com.richieoscar.agrologistics.config;

import com.richieoscar.agrologistics.domain.Staff;
import com.richieoscar.agrologistics.repository.StaffRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.Collections;
import java.util.List;
import java.util.Optional;

@Service
public class CustomUserDetailsService implements UserDetailsService {

    @Autowired
    private StaffRepository staffRepository;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        Optional<Staff> optionalStaff = staffRepository.findByEmail(username);
        if (optionalStaff.isPresent()) {
            Staff staff = optionalStaff.get();
            return new User(staff.getEmail(), staff.getPassword(), List.of(new SimpleGrantedAuthority(staff.getRole().name())));
        }
        return null;
    }
}
