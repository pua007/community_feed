package org.fastcampus.post.repository;

import org.fastcampus.post.appication.interfaces.CommentRepository;
import org.fastcampus.post.domain.comment.Comment;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public class CommentRepositoryImpl implements CommentRepository {
    @Override
    public Comment save(Comment comment) {
        return null;
    }

    @Override
    public Optional<Comment> findById(Long id) {
        return Optional.empty();
    }
}
