package soo.demo.domain;

import lombok.Getter;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
@Getter
public enum StoryDonationTarget {
    EMPLOYEE("임직원만"),
    WITH_USERS("사용자도 함께");

    private final String name;
}
