package com.shl.store.repositories;

import org.springframework.data.repository.CrudRepository;

import com.shl.store.entities.User;

public interface UserRepository extends CrudRepository<User, Long> {

}
