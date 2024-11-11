package org.fastcampus.post.repository.post_queue;

import com.querydsl.core.types.Projections;
import com.querydsl.core.types.dsl.BooleanExpression;
import com.querydsl.jpa.impl.JPAQueryFactory;
import java.util.List;
import lombok.RequiredArgsConstructor;
import org.fastcampus.post.repository.entity.like.QLikeEntity;
import org.fastcampus.post.repository.entity.post.QPostEntity;
import org.fastcampus.post.repository.entity.post.QUserPostQueueEntity;
import org.fastcampus.post.ui.dto.GetPostContentResponseDto;
import org.fastcampus.user.repository.entity.QUserEntity;
import org.springframework.stereotype.Repository;

@Repository
@RequiredArgsConstructor
public class UserPostQueueQueryRepositoryImpl implements UserPostQueueQueryRepository{
    private final JPAQueryFactory queryFactory;
    private static final QUserPostQueueEntity userPostQueueEntity = QUserPostQueueEntity.userPostQueueEntity;
    private static final QPostEntity postEntity = QPostEntity.postEntity;
    private static final QUserEntity userEntity = QUserEntity.userEntity;
    private static final QLikeEntity likeEntity = QLikeEntity.likeEntity;


    @Override
    public List<GetPostContentResponseDto> getContentResponse(Long userId, Long lastContentId) {
        return queryFactory
            .select(
                Projections.fields(//DTO에 매핑하는 방식
                    GetPostContentResponseDto.class,
                    postEntity.id.as("id"),
                    postEntity.content.as("content"),
                    userEntity.id.as("userId"),
                    userEntity.name.as("userName"),
                    userEntity.profileImage.as("userProfileImage"),
                    postEntity.regDt.as("createdAt"),
                    postEntity.updDt.as("updatedAt"),
                    postEntity.commentCount.as("commentCount"),
                    postEntity.likeCount.as("likeCount"),
                    likeEntity.isNotNull().as("isLikedByMe")
                )
            )
            .from(userPostQueueEntity)//유저포스트 큐테이블을 기준으로
            .join(postEntity).on(userPostQueueEntity.postId.eq(postEntity.id))//포스트테이블의 아이디 값과 유저포스트큐테이블이 가지고 있는 포스트아이디값을 통해 조인
            .join(userEntity).on(userPostQueueEntity.authorId.eq(userEntity.id))//유저테이블의 아이디 값과 유저 포스트 큐테이블이 가고 있는 유저유저 아이디값을 통해 조인
            .leftJoin(likeEntity).on(hasLike(userId))//내가이 게시글에 좋아요를 눌렀는지 않눌렀는지 조회를 하고있음
            .where(
                userPostQueueEntity.userId.eq(userId),
                hasLastData(lastContentId)
            )
            .orderBy(userPostQueueEntity.postId.desc())
            .limit(20)
            .fetch();
    }

    private BooleanExpression hasLastData(Long lastId){
        if(lastId == null){
            return null;
        }
        return postEntity.id.lt(lastId);
    }

    private BooleanExpression hasLike(Long userId){
        if(userId == null){
            return null;
        }

        return postEntity.id
            .eq(likeEntity.id.targetId)
            .and(likeEntity.id.targetType.eq("POST"))
            .and(likeEntity.id.userId.eq(userId));
    }
}
