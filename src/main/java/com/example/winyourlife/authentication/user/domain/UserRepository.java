package com.example.winyourlife.authentication.user.domain;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.UUID;

@Repository
interface UserRepository extends JpaRepository<User, UUID> {
    Boolean existsByEmail(String email);

    java.util.Optional<User> findByEmail(String email);

    java.util.Optional<User> findByUuid(java.util.UUID uuid);
}
