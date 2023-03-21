package com.richieoscar.agrologistics.repository;

import com.richieoscar.agrologistics.domain.Route;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface RouteRepository extends JpaRepository<Route, Long> {

    List<Route> findAllByLocation_Id(Long locationId);
    Page<Route> findAllByLocation_Id(Long locationId, Pageable pageable);

    Optional<Route> findByName(String routeName);
}
