package org.fastcampus.user.application.dto;

import org.fastcampus.user.domain.User;

public record GetUserResponseDto(Long id, String name, String profileImage, Integer followingCount, Integer followerCount) {
    public GetUserResponseDto(User user){
        this(user.getId(), user.getName(), user.getProfileImageUrl(), user.getFollowingCount(), user.getFollowerCounter());
    }
}
