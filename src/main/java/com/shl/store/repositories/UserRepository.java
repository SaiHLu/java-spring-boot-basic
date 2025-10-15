package com.shl.store.repositories;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.EntityGraph;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;

import com.shl.store.dtos.UserSummary;
import com.shl.store.entities.User;

public interface UserRepository extends CrudRepository<User, Long> {
    @EntityGraph(attributePaths = { "tags" })
    Optional<User> findByEmail(String email);

    @EntityGraph(attributePaths = { "addresses" })
    @Query("select u from User u")
    List<User> findUsersWithAddresses();

    @Query("select u.id as id, u.email as email from User u where u.profile.loyaltyPoints > :loyaltyPoints order by u.email")
    List<UserSummary> findLoyalUsers(@Param("loyaltyPoints") Integer loyaltyPoints);
}
