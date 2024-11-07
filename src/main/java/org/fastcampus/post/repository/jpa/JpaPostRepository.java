package org.fastcampus.post.repository.jpa;

import jakarta.transaction.Transactional;
import  org.fastcampus.post.domain.Post;
import org.fastcampus.post.repository.entity.post.PostEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

public interface JpaPostRepository extends JpaRepository<PostEntity, Long> {
    @Modifying
    @Query(value = "UPDATE PostEntity p "
                + "SET p.content = :#{#postEntity.getContent()},"
                + "p.state = :#{#postEntity.getState()}, "
                + "p.upDt = now() "
                + "WHERE p.id = :#{#postEntity.id}")
    void updatePostEntity(PostEntity postEntity);
}
