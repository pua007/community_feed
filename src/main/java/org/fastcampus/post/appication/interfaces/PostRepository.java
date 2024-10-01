package org.fastcampus.post.appication.interfaces;

import org.fastcampus.post.domain.Post;

import java.util.Optional;

public interface PostRepository {

    Post save(Post post);

    Optional<Post> findById(Long Id);
}
