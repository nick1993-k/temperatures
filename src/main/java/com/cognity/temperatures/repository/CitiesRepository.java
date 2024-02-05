package com.cognity.temperatures.repository;

import com.cognity.temperatures.entities.Cities;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CitiesRepository extends JpaRepository<Cities,Long> {
}
