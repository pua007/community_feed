package org.fastcampus.post.repository.post_queue;

import java.util.List;
import org.fastcampus.post.ui.dto.GetpostContentResponseDto;

public interface UserPostQueueQueryRepository {

    List<GetpostContentResponseDto> getContentResponse(Long userId, Long lastPostId);

}
