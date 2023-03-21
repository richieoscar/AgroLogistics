package com.richieoscar.agrologistics.repository;

import com.richieoscar.agrologistics.domain.Staff;
import com.richieoscar.agrologistics.enumeration.Role;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.stereotype.Component;

import java.util.Optional;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;

@DataJpaTest
class StaffRepositoryTest {

    @Autowired
    private StaffRepository underTest;

    @Test
    void testSaveStaff() {
        //Given
        Staff staff = new Staff();
        staff.setId(2L);
        staff.setEmail("any@gmail.com");
        staff.setRole(Role.USER);
        staff.setLastName("AnyOKpo");
        staff.setPassword("password");

        //When
        Staff savedStaff = underTest.save(staff);
        Optional<Staff> optionalStaff = underTest.findById(savedStaff.getId());
        assertThat(optionalStaff).isPresent()
                .hasValueSatisfying(staff1 -> {
                    assertThat(staff1.getId().equals(staff.getId()));
                    assertThat(staff1.getEmail().equals(staff.getEmail()));
                });
    }
}