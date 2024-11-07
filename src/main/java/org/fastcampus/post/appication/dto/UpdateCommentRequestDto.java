package org.fastcampus.post.appication.dto;

import org.fastcampus.user.domain.User;

public record UpdateCommentRequestDto(Long userId, String content) {
}
