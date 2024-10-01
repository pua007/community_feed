package org.fastcampus.post.appication.dto;

import org.fastcampus.post.domain.content.PostPublicationState;

public record CreatePostRequestDto(Long userId, String Content, PostPublicationState state) {
}
