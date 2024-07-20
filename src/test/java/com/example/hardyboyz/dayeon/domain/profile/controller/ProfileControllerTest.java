package com.example.hardyboyz.dayeon.domain.profile.controller;

import com.example.hardyboyz.dayeon.domain.profile.dto.request.UpdateProfileRequest;
import com.example.hardyboyz.dayeon.domain.profile.dto.response.UpdateProfileResponse;
import com.example.hardyboyz.dayeon.domain.profile.service.ProfileService;
import com.example.hardyboyz.dayeon.domain.profile.type.Gender;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyLong;
import static org.mockito.BDDMockito.given;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.put;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@WebMvcTest(ProfileController.class)
class ProfileControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @Autowired
    private ObjectMapper objectMapper;

    @MockBean
    private ProfileService profileService;

    @Test
    @DisplayName("[프로필 수정] - 성공 검증")
    void updateProfile_success() throws Exception {
        // given
        Long memberId = 2L;
        UpdateProfileRequest updateProfileRequest = new UpdateProfileRequest(
                "최성욱", "첫 소개", "profileImage.jpg");
        UpdateProfileResponse updateProfileResponse = new UpdateProfileResponse(
                2L, "새로운닉네임",
                "안녕하세요..", "newProfileImage.jpg"
                , 0L, 0L, Gender.MALE);
        String request = objectMapper.writeValueAsString(updateProfileRequest);

        given(profileService.update(anyLong(), any(UpdateProfileRequest.class))).willReturn(updateProfileResponse);

        // when & then
        mockMvc.perform(put("/api/oh-woon-wan/profile/{memberId}", memberId)
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(request))
                .andExpect(status().isOk())
                .andDo(print())
                .andExpect(jsonPath("$.nickname").value("새로운닉네임"))
                .andExpect(jsonPath("$.introduction").value("안녕하세요.."))
                .andExpect(jsonPath("$.profileImage").value("newProfileImage.jpg"))
                .andExpect(jsonPath("$.id").value(2));
    }

    @Test
    @DisplayName("[프로필 수정] - 닉네임이 짧아서 실패 한 경우")
    void updateProfile_nicknameTooShort() throws Exception {
        // given
        Long memberId = 2L;
        UpdateProfileRequest updateProfileRequest = new UpdateProfileRequest(
                "최", "첫 소개", "profileImage.jpg");

        String request = objectMapper.writeValueAsString(updateProfileRequest);

        // when & then
        mockMvc.perform(put("/api/oh-woon-wan/profile/{memberId}", memberId)
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(request))
                .andExpect(status().isBadRequest())
                .andExpect(jsonPath("$.nickname").value("닉네임은 2자 이상 20자 이내여야 합니다."));
    }

}