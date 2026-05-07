package com.univeristy.ms.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.univeristy.ms.entity.Address;

@Repository
public interface AddressRepository extends JpaRepository<Address, Long> {

}
