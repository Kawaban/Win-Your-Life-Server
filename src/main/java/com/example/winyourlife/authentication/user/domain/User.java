package com.example.winyourlife.authentication.user.domain;

import com.example.winyourlife.infrastructure.model.AbstractEntity;
import jakarta.persistence.*;
import lombok.Builder;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.time.Instant;
import java.util.Collection;
import java.util.Collections;
import java.util.UUID;

@EqualsAndHashCode(callSuper = true)
@Data
@Entity
@NoArgsConstructor
@Table(name = "users")
class User extends AbstractEntity implements UserDetails {
    @Column(nullable = false)
    private char[] password;

    @Column(unique = true, nullable = false)
    private String email;

    @Enumerated(EnumType.STRING)
    @Column(nullable = false)
    private Role role;

    @Column(nullable = false)
    private boolean isEnabled = true;

    @Builder
    public User(
            UUID uuid,
            Long version,
            Instant createdDate,
            Instant lastModifiedDate,
            char[] password,
            String email,
            Role role,
            boolean isEnabled) {
        super(uuid, version, createdDate, lastModifiedDate);
        this.password = password;
        this.email = email;
        this.role = role;
        this.isEnabled = true;
    }

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return Collections.singleton(role);
    }

    @Override
    public String getUsername() {
        return email;
    }

    @Override
    public String getPassword() {
        return new String(password);
    }


    @Override
    public boolean isAccountNonLocked() {
        return isEnabled;
    }


    @Override
    public boolean isEnabled() {
        return isEnabled;
    }
}