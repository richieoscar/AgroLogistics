package com.richieoscar.agrologistics.repository;

import com.richieoscar.agrologistics.domain.Location;
import com.richieoscar.agrologistics.domain.Route;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;

import java.util.List;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;
import static org.junit.jupiter.api.Assertions.*;
@DataJpaTest
class RouteRepositoryTest {

    @Autowired
    private RouteRepository underTest;

    @Autowired
    private LocationRepository locationRepository;


    @Test
    void testFindRoteByLocationId(){
       //Given
        Location location = new Location();
        location.setLatitude("099939933");
        location.setLongitude("9393993");
        location.setName("Fadeyi");
        Location save = locationRepository.save(location);

        Route route = new Route();
        route.setName("Yaba_Lekki");
        route.setTraffic(false);
        route.setPath("yaba-lekki");
        route.setTime(2);
        route.setDistance(100);
        route.setLocation(save);
        underTest.save(route);
       //where
        List<Route> routes = underTest.findAllByLocation_Id(save.getId());

        //then
        assertThat(routes).isNotNull();
        assertEquals(routes.size(),1);
    }

}