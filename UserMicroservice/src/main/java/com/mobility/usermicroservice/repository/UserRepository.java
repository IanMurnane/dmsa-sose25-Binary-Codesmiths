package com.mobility.usermicroservice.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.mobility.usermicroservice.entity.User;

import java.util.Optional;

public interface UserRepository extends JpaRepository<User, Long> {
    Optional<User> findByEmail(String email);
}
