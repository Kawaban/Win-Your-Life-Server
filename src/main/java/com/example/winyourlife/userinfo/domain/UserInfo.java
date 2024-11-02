package com.example.winyourlife.userinfo.domain;

import com.example.winyourlife.infrastructure.model.AbstractEntity;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Table;
import jakarta.validation.constraints.Email;
import lombok.Builder;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import jakarta.persistence.Lob;

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

    @Lob
    @Column(columnDefinition = "BYTEA")
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
            byte[] avatar,
            int streak,
            int longestStreak,
            int completedTasks,
            boolean isFriendNotificationActive,
            boolean isDailyReminderActive) {
        super(uuid, version, createdDate, lastModifiedDate);
        this.name = name;
        this.email = email;
        this.avatar = avatar;
        this.streak = streak;
        this.longestStreak = longestStreak;
        this.completedTasks = completedTasks;
        this.isFriendNotificationActive = isFriendNotificationActive;
        this.isDailyReminderActive = isDailyReminderActive;
    }
}
