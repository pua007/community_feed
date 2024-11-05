package org.fastcampus.post.repository.jpa;

import org.fastcampus.post.repository.entity.comment.CommentEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;


public interface JpaCommentRepository extends JpaRepository<CommentEntity, Long> {
}
