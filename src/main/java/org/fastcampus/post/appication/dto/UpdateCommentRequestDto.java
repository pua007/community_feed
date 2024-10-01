package org.fastcampus.post.appication.dto;

import org.fastcampus.user.domain.User;

public record UpdateCommentRequestDto(Long commentId, Long userId, String content) {
}
