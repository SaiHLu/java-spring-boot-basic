package com.shl.store.repositories;

import org.springframework.data.repository.CrudRepository;

import com.shl.store.entities.Profile;

public interface ProfileRepository extends CrudRepository<Profile, Long> {

}
