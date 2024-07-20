package com.example.hardyboyz.dayeon.domain.profile.dto.response;

import com.example.hardyboyz.dayeon.domain.profile.entity.Profile;
import com.example.hardyboyz.dayeon.domain.profile.type.Gender;
import lombok.Builder;
import lombok.Getter;
import lombok.RequiredArgsConstructor;

@Getter
@Builder
@RequiredArgsConstructor
public class UpdateProfileResponse {

    private final Long id;

    private final String nickname;

    private final String introduction;

    private final String profileImage;

    private final Long follower;

    private final Long following;

    private final Gender gender;

    public static UpdateProfileResponse from(Profile profile) {
        return UpdateProfileResponse.builder()
                .id(profile.getId())
                .nickname(profile.getNickname())
                .introduction(profile.getIntroduction())
                .profileImage(profile.getProfileImage())
                .follower(profile.getFollower())
                .following(profile.getFollowing())
                .gender(profile.getGender())
                .build();
    }

}
