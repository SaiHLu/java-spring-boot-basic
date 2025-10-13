package com.shl.store.repositories;

import org.springframework.data.repository.CrudRepository;

import com.shl.store.entities.Address;

public interface AddressRepository extends CrudRepository<Address, Long> {

}
