package com.example.hardyboyz.dayeon.domain.profile.entity;

import com.example.hardyboyz.dayeon.domain.profile.dto.request.UpdateProfileRequest;
import com.example.hardyboyz.dayeon.domain.profile.type.Gender;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Entity
@Getter
@NoArgsConstructor
@AllArgsConstructor
public class Profile {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String nickname;

    private String introduction;

    private String profileImage;

    private Long follower;

    private Long following;

    @Enumerated(EnumType.STRING)
    private Gender gender;

    public void updateProfile(UpdateProfileRequest updateProfileRequest) {
        if (updateProfileRequest.getNickname() != null) {
            this.nickname = updateProfileRequest.getNickname();
        }
        if (updateProfileRequest.getIntroduction() != null) {
            this.introduction = updateProfileRequest.getIntroduction();
        }
        if (updateProfileRequest.getProfileImage() != null) {
            this.profileImage = updateProfileRequest.getProfileImage();
        }
    }

}
