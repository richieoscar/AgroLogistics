package com.richieoscar.agrologistics.repository;

import com.richieoscar.agrologistics.domain.Location;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;

import java.util.Optional;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;

@DataJpaTest
class LocationRepositoryTest {

    @Autowired
    private LocationRepository underTest;

    @Test
    void testFindLocationByName() {

        //Given
        Location location = new Location();
        location.setLatitude("099939933");
        location.setLongitude("9393993");
        location.setName("Fadeyi");
        underTest.save(location);

        Optional<Location> locationOptional = underTest.findByName("Fadeyi");
        assertThat(locationOptional).isPresent()
                .hasValueSatisfying(location1 -> {
                    assertThat(location1.getName().equals(location.getName()));
                });
    }
}