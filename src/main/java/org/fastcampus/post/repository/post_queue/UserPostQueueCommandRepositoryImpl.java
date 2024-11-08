package org.fastcampus.post.repository.post_queue;

import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.fastcampus.post.repository.entity.post.PostEntity;
import org.fastcampus.post.repository.entity.post.UserPostQueueEntity;
import org.fastcampus.post.repository.jpa.JpaPostRepository;
import org.fastcampus.post.repository.jpa.JpaUserPostQueueRepository;
import org.fastcampus.user.repository.entity.UserEntity;
import org.fastcampus.user.repository.jpa.JpaUserRelationRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
@RequiredArgsConstructor
public class UserPostQueueCommandRepositoryImpl implements UserPostQueueCommandRepository{

    private final JpaPostRepository jpaPostRepository;
    private final JpaUserRelationRepository jpaUserRelationRepository;
    private final JpaUserPostQueueRepository jpaUserPostQueueRepository;


    @Override
    @Transactional
    public void publishPost(PostEntity postEntity) {
        UserEntity userEntity = postEntity.getAuthor();
        //글쓴 작가를 팔로워하는 아이디를 찾아온다.
        List<Long> followersIds = jpaUserRelationRepository.findFollowers(userEntity.getId());

        List<UserPostQueueEntity> userPostQueueEntityList = followersIds.stream()
                .map(userId -> new UserPostQueueEntity(userId, postEntity.getId(), userEntity.getId()))
                .toList();
        jpaUserPostQueueRepository.saveAll(userPostQueueEntityList);
    }

    @Override
    @Transactional
    public void saveFollowPost(Long userId, Long targetId) {
        List<Long> postIdList = jpaPostRepository.findAllPostIdByAuthorId(targetId);
        List<UserPostQueueEntity> userPostQueueEntityList = postIdList.stream().map(postId -> new UserPostQueueEntity(userId, postId, targetId)).toList();
        jpaUserPostQueueRepository.saveAll(userPostQueueEntityList);
    }

    @Override
    @Transactional
    public void deleteUnfollowPost(Long userId, Long targetId) {
        jpaUserPostQueueRepository.deleteAllByUserIdAndAuthorId(userId, targetId);
    }
}
