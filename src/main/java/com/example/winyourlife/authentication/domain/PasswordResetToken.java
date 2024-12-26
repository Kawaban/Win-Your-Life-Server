package com.example.winyourlife.authentication.domain;

import com.example.winyourlife.infrastructure.model.AbstractEntity;
import jakarta.persistence.Entity;
import jakarta.persistence.Table;
import jakarta.persistence.Transient;
import java.time.Instant;
import java.util.UUID;
import lombok.Builder;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

@EqualsAndHashCode(callSuper = true)
@Data
@Entity
@Table(name = "password_reset_tokens")
@NoArgsConstructor
class PasswordResetToken extends AbstractEntity {

    private static final int EXPIRATION_IN_SECONDS = 24 * 60 * 60;

    private String token;
    private UUID userUuid;
    private Instant expirationDate;
    private boolean isUsed;

    @Builder
    public PasswordResetToken(
            UUID uuid,
            Long version,
            Instant createdDate,
            Instant lastModifiedDate,
            String token,
            UUID userUUID,
            Instant expirationDate) {
        super(uuid, version, createdDate, lastModifiedDate);
        this.token = token;
        this.userUuid = userUUID;
        if (expirationDate != null) {
            this.expirationDate = expirationDate;
        } else {
            this.expirationDate = Instant.now().plusSeconds(EXPIRATION_IN_SECONDS);
        }
        isUsed = false;
    }

    @Transient
    public boolean isExpired() {
        return expirationDate.isBefore(Instant.now());
    }
}
