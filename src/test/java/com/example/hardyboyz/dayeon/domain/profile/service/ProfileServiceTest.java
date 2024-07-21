package com.example.hardyboyz.dayeon.domain.profile.service;

import com.example.hardyboyz.dayeon.domain.member.exception.UserNotFoundException;
import com.example.hardyboyz.dayeon.domain.profile.dto.request.UpdateProfileRequest;
import com.example.hardyboyz.dayeon.domain.profile.dto.response.ProfileResponse;
import com.example.hardyboyz.dayeon.domain.profile.dto.response.UpdateProfileResponse;
import com.example.hardyboyz.dayeon.domain.profile.entity.Profile;
import com.example.hardyboyz.dayeon.domain.profile.repository.ProfileRepository;
import com.example.hardyboyz.dayeon.domain.profile.type.Gender;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.Optional;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;
import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.BDDMockito.given;
import static org.mockito.Mockito.verify;

@ExtendWith(MockitoExtension.class)
class ProfileServiceTest {

    @InjectMocks
    private ProfileService profileService;

    @Mock
    private ProfileRepository profileRepository;

    @Test
    @DisplayName("[프로필 수정] - 성공 검증")
    void updateProfile_success() {
        // given
        Long memberId = 2L;
        UpdateProfileRequest updateProfileRequest = new UpdateProfileRequest(
                "최성욱", "첫 소개", "profileImage.jpg");

        Profile existingProfile = new Profile(
                memberId, "욱기지요",
                "안녕하세요.", "newProfileImage.jpg",
                0L, 0L,
                Gender.MALE);

        given(profileRepository.findById(memberId)).willReturn(Optional.of(existingProfile));

        Profile updateProfile = new Profile(
                memberId, "최성욱",
                "첫 소개", "profileImage.jpg",
                0L, 0L,
                Gender.MALE);

        given(profileRepository.save(existingProfile)).willReturn(updateProfile);

        // when
        UpdateProfileResponse response = profileService.update(memberId, updateProfileRequest);

        // then
        assertNotNull(response);
        UpdateProfileResponse expectedResponse = UpdateProfileResponse.from(updateProfile);
        assertThat(response).usingRecursiveComparison().isEqualTo(expectedResponse);
        verify(profileRepository).findById(memberId);
        verify(profileRepository).save(existingProfile);
    }

    @Test
    @DisplayName("[프로필 수정] - 사용자가 존재하지 않는 경우 UserNotFoundException 발생")
    void updateProfile_failed_then_userNotFoundException() {
        // given
        Long memberId = 7L;
        UpdateProfileRequest updateProfileRequest = new UpdateProfileRequest(
                "최성욱", "첫 소개", "profileImage.jpg");

        given(profileRepository.findById(memberId)).willReturn(Optional.empty());

        // when & then
        assertThrows(UserNotFoundException.class,
                () -> profileService.update(memberId, updateProfileRequest));

        verify(profileRepository).findById(memberId);
    }

    @Test
    @DisplayName("[프로필 조회] - 성공 검증")
    void findProfile_success() {
        //given
        Long memberId = 3L;
        Profile profile = new Profile(
                memberId, "최성욱",
                "첫 소개", "profileImage.jpg",
                1L, 1L,
                Gender.MALE);

        given(profileRepository.findById(memberId)).willReturn(Optional.of(profile));

        // when
        ProfileResponse expectedResponse = profileService.findProfile(memberId);

        // then
        assertThat(expectedResponse).isNotNull();
        assertThat(expectedResponse.getId()).isEqualTo(memberId);
        assertThat(expectedResponse.getNickname()).isEqualTo("최성욱");
        assertThat(expectedResponse.getIntroduction()).isEqualTo("첫 소개");
        assertThat(expectedResponse.getProfileImage()).isEqualTo("profileImage.jpg");
        assertThat(expectedResponse.getFollower()).isEqualTo(1L);
        assertThat(expectedResponse.getFollowing()).isEqualTo(1L);
        assertThat(expectedResponse.getGender()).isEqualTo(Gender.MALE);
    }

    @Test
    @DisplayName("[프로필 조회] - 사용자가 존재하지 않을 경우 UserNotFoundException 발생")
    void findProfile_failed_then_userNotFoundException() {
        // given
        Long memberId = 3L;
        given(profileRepository.findById(memberId)).willReturn(Optional.empty());

        // when & then
        assertThrows(UserNotFoundException.class,
                () -> profileService.findProfile(memberId));

        verify(profileRepository).findById(memberId);
    }


}