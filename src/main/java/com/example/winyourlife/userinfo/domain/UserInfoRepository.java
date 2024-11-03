package com.example.winyourlife.userinfo.domain;

import java.util.Optional;
import java.util.UUID;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
interface UserInfoRepository extends JpaRepository<UserInfo, UUID> {
    Boolean existsByEmail(String email);

    Optional<UserInfo> findByEmail(String email);

    Optional<UserInfo> findByUuid(UUID uuid);
}
