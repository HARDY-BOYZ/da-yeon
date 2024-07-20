package com.example.hardyboyz.dayeon.domain.profile.type;

import lombok.Getter;
import lombok.RequiredArgsConstructor;

@Getter
@RequiredArgsConstructor
public enum Gender {

    MALE("남자"),
    FEMALE("여자"),
    ;

    private final String description;

}
