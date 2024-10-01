package org.fastcampus.post.appication.dto;

import org.fastcampus.post.domain.content.PostPublicationState;

public record UpdatePostRequestDto(Long postId, Long userId, String content, PostPublicationState state) {
}
