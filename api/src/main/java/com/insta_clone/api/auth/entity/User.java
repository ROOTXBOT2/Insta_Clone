package com.insta_clone.api.auth.entity;

import jakarta.persistence.*;
import lombok.*;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import java.time.LocalDateTime;

/**
 * @author rua
 */
@Entity
@EntityListeners(AuditingEntityListener.class)
@AllArgsConstructor
@Builder
@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED) // JPA용
public class User {
    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false, unique = true)
    private String email;

    @Column(nullable = false)
    private String password;

    @Column (nullable = false)
    private String username;

    @Column(length = 1000)
    private String profileImage;

    @Column(length = 1000)
    private String bio;

    @Column @CreatedDate
    private LocalDateTime createdAt;

    @Column @LastModifiedDate
    private LocalDateTime updatedAt;

    @Column(nullable = false)
    private boolean isDeleted = false;

    @Column
    private LocalDateTime deletedAt;

    // 프로필 정보 변경 메서드
    public void updateProfile(String username, String bio, String profileImage) {
        if (username != null) this.username = username;
        if (bio != null) this.bio = bio;
        if (profileImage != null) this.profileImage = profileImage;
    }
}
