package org.fastcampus.post.repository;

import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.fastcampus.post.appication.interfaces.CommentRepository;
import org.fastcampus.post.domain.Post;
import org.fastcampus.post.domain.comment.Comment;
import org.fastcampus.post.repository.entity.comment.CommentEntity;
import org.fastcampus.post.repository.jpa.JpaCommentRepository;
import org.fastcampus.post.repository.jpa.JpaPostRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
@RequiredArgsConstructor
public class CommentRepositoryImpl implements CommentRepository {

    private final JpaCommentRepository jpaCommentRepository;
    private final JpaPostRepository jpaPostRepository;

    @Override
    @Transactional
    public Comment save(Comment comment) {
        Post targetPost = comment.getPost();
        CommentEntity entity = new CommentEntity(comment);
        if(comment.getId() != null){
            jpaCommentRepository.updateCommentEntity(entity);
            return entity.toComment();
        }
        entity = jpaCommentRepository.save(entity);
        jpaPostRepository.increaseCommentCount(targetPost.getId());
        return entity.toComment();
    }

    @Override
    public Comment findById(Long id) {
        CommentEntity commententity = jpaCommentRepository.findById(id).orElseThrow();
        return commententity.toComment();
    }
}
