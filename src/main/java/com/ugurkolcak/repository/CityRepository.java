package com.ugurkolcak.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.ugurkolcak.entities.City;

public interface CityRepository extends JpaRepository<City, String> {

}
