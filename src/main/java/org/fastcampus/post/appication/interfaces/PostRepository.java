package org.fastcampus.post.appication.interfaces;

import org.fastcampus.post.domain.Post;

public interface PostRepository {

    Post save(Post post);

    Post findById(Long id);
}
