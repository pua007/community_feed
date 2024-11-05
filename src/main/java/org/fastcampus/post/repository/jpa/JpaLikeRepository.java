package org.fastcampus.post.repository.jpa;

import org.fastcampus.post.repository.entity.like.LikeEntity;
import org.fastcampus.post.repository.entity.like.LikeIdEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

public interface JpaLikeRepository extends JpaRepository<LikeEntity, LikeIdEntity> {
}
