package org.fastcampus.post.appication.dto;

import org.fastcampus.post.domain.PostPublicationState;

public record UpdatePostRequestDto(Long userId, String content, PostPublicationState state) {
}
