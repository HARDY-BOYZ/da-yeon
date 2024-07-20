package com.example.hardyboyz.dayeon.domain.profile.service;

import com.example.hardyboyz.dayeon.domain.member.exception.UserNotFoundException;
import com.example.hardyboyz.dayeon.domain.profile.dto.request.UpdateProfileRequest;
import com.example.hardyboyz.dayeon.domain.profile.dto.response.UpdateProfileResponse;
import com.example.hardyboyz.dayeon.domain.profile.entity.Profile;
import com.example.hardyboyz.dayeon.domain.profile.repository.ProfileRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
public class ProfileService {

    private final ProfileRepository profileRepository;

    @Transactional
    public UpdateProfileResponse update(Long memberId,
                                        UpdateProfileRequest updateProfileRequest) {
        Profile profile = profileRepository.findById(memberId)
                .orElseThrow(UserNotFoundException::new);
        profile.updateProfile(updateProfileRequest);
        return UpdateProfileResponse.from(profileRepository.save(profile));
    }

}
