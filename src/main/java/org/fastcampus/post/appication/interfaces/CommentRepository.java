package org.fastcampus.post.appication.interfaces;

import org.fastcampus.post.domain.comment.Comment;

import java.util.Optional;

public interface CommentRepository {

    Comment save(Comment comment);
    Optional<Comment> findById(Long id);
}
