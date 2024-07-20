package com.example.hardyboyz.dayeon.domain.profile.dto.request;

import jakarta.validation.constraints.Size;
import lombok.Getter;
import lombok.RequiredArgsConstructor;

@Getter
@RequiredArgsConstructor
public class UpdateProfileRequest {

    @Size(min = 2, max = 20, message = "닉네임은 2자 이상 20자 이내여야 합니다.")
    private final String nickname;

    @Size(max = 200, message = "소개는 200자 이내여야 합니다.")
    private final String introduction;

    private final String profileImage;

}
