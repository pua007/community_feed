package org.fastcampus.post.repository;

import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.fastcampus.post.appication.interfaces.PostRepository;
import org.fastcampus.post.domain.Post;
import org.fastcampus.post.repository.entity.post.PostEntity;
import org.fastcampus.post.repository.jpa.JpaPostRepository;
import org.springframework.stereotype.Repository;

@Repository
@RequiredArgsConstructor
public class PostRepositoryImpl implements PostRepository {

    private final JpaPostRepository postRepository;

    @Override
    @Transactional
    public Post save(Post post) {
        PostEntity postEntity = new PostEntity(post);
        postEntity = postRepository.save(postEntity);
        return postEntity.toPost();
    }

    @Override
    public Post findById(Long id) {
        PostEntity postEntity = postRepository.findById(id).orElseThrow();
        return postEntity.toPost();
    }
}
