package com.example.winyourlife.notification.domain;

import com.example.winyourlife.infrastructure.model.AbstractEntity;
import com.example.winyourlife.userinfo.domain.UserInfo;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import java.time.Instant;
import java.util.UUID;
import lombok.Builder;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

@EqualsAndHashCode(callSuper = true)
@Entity
@Data
@NoArgsConstructor
@Table(name = "friend_requests")
public class FriendRequest extends AbstractEntity {
    @ManyToOne(fetch = FetchType.LAZY)
    private UserInfo sender;

    @ManyToOne(fetch = FetchType.LAZY)
    private UserInfo receiver;

    @Builder
    public FriendRequest(
            UUID uuid,
            Long version,
            Instant createdDate,
            Instant lastModifiedDate,
            UserInfo sender,
            UserInfo receiver) {
        super(uuid, version, createdDate, lastModifiedDate);
        this.sender = sender;
        this.receiver = receiver;
    }
}
