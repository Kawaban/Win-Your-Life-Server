package com.example.winyourlife.userinfo.domain;

import com.example.winyourlife.infrastructure.model.AbstractEntity;
import jakarta.persistence.*;
import jakarta.validation.constraints.Email;
import java.time.Instant;
import java.util.List;
import java.util.UUID;
import lombok.Builder;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

@EqualsAndHashCode(callSuper = true)
@Entity
@Data
@NoArgsConstructor
@Table(name = "users_info")
public class UserInfo extends AbstractEntity {

    @Column(nullable = false)
    private String name;

    @Column(columnDefinition = "bytea")
    private byte[] avatar;

    @Column(nullable = false)
    private int streak;

    @Column(nullable = false)
    private int longestStreak;

    @Column(nullable = false)
    private int completedTasks;

    @Column(nullable = false)
    private int wonDays;

    @Email
    @Column(nullable = false, unique = true)
    private String email;

    @ManyToMany(fetch = FetchType.LAZY)
    @JoinTable(
            name = "friends",
            joinColumns = @JoinColumn(name = "user_id"),
            inverseJoinColumns = @JoinColumn(name = "friend_id"))
    private List<UserInfo> friends;

    @Builder
    public UserInfo(
            UUID uuid,
            Long version,
            Instant createdDate,
            Instant lastModifiedDate,
            String name,
            String email,
            int streak,
            int longestStreak,
            int completedTasks,
            int wonDays) {
        super(uuid, version, createdDate, lastModifiedDate);
        this.name = name;
        this.email = email;
        this.avatar = new byte[0];
        this.streak = streak;
        this.longestStreak = longestStreak;
        this.completedTasks = completedTasks;
        this.wonDays = wonDays;
    }
}
