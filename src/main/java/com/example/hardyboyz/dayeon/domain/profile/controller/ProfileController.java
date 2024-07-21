package com.example.hardyboyz.dayeon.domain.profile.controller;

import com.example.hardyboyz.dayeon.domain.profile.dto.request.UpdateProfileRequest;
import com.example.hardyboyz.dayeon.domain.profile.dto.response.UpdateProfileResponse;
import com.example.hardyboyz.dayeon.domain.profile.service.ProfileService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/oh-woon-wan/profile")
public class ProfileController {

    private final ProfileService profileService;

    /**
     * 회원 적용 시 id 값 에서 AuthenticationPrincipal 적용 예정
     *
     * @param memberId Long -> @AuthenticationPrincipal
     * @return UpdateProfileResponse 반환
     */
    @PutMapping("/{memberId}")
    public UpdateProfileResponse update(
            @PathVariable Long memberId,
            @Valid @RequestBody UpdateProfileRequest updateProfileRequest) {
        return profileService.update(memberId, updateProfileRequest);
    }

}
