package com.example.winyourlife.userinfo.domain;

import com.example.winyourlife.infrastructure.model.AbstractEntity;
import jakarta.persistence.*;
import jakarta.validation.constraints.Email;
import lombok.Builder;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.JdbcTypeCode;
import org.hibernate.annotations.LazyGroup;
import org.hibernate.annotations.Type;
import org.hibernate.type.SqlTypes;

import java.sql.Blob;
import java.time.Instant;
import java.util.UUID;

@EqualsAndHashCode(callSuper = true)
@Entity
@Data
@NoArgsConstructor
@Table(name = "users_info")
public class UserInfo extends AbstractEntity {

    @Column(nullable = false)
    private String name;

    @Column(columnDefinition="bytea")
    private byte[] avatar;

    private int streak;
    private int longestStreak;
    private int completedTasks;

    @Column(nullable = false)
    private boolean isFriendNotificationActive = true;

    @Column(nullable = false)
    private boolean isDailyReminderActive = true;

    @Email
    @Column(nullable = false, unique = true)
    private String email;


    @Builder
    public UserInfo(
            UUID uuid,
            Long version,
            Instant createdDate,
            Instant lastModifiedDate,
            UUID userId,
            String name,
            String email,
            int streak,
            int longestStreak,
            int completedTasks,
            boolean isFriendNotificationActive,
            boolean isDailyReminderActive) {
        super(uuid, version, createdDate, lastModifiedDate);
        this.name = name;
        this.email = email;
        this.avatar = new byte[0];
        this.streak = streak;
        this.longestStreak = longestStreak;
        this.completedTasks = completedTasks;
        this.isFriendNotificationActive = isFriendNotificationActive;
        this.isDailyReminderActive = isDailyReminderActive;
    }
}
