package com.ugurkolcak.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.ugurkolcak.entities.Address;

public interface AddressRepository extends JpaRepository<Address, Long> {

}
