package org.fastcampus.user.repository.jpa;

import org.fastcampus.user.application.dto.GetUserListResponseDto;
import org.fastcampus.user.repository.entity.UserEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface JpaUserListQueryRepository extends JpaRepository<UserEntity, Long>{
    //:을 통해서 넘겨주는 userId변수임을 나타내주면 된다.

    //Dto로 받지 않고 값으로도 받을수 있다 u, ur 만약 특정 데이터베이스에서 제공하는 쿼리문을 쓰고 싶다면 nativeQuery = true 옵션을 주고 쓰면된다.
    @Query(value = "SELECT new org.fastcampus.user.application.dto.GetUserListResponseDto(u.name, u.profileImage)"
            +" FROM UserRelationEntity ur"
            +" INNER JOIN UserEntity u ON ur.followerUserId = u.id"
            +" WHERE ur.followingUserId = :userId")
    List<GetUserListResponseDto> getFollowingUserList(Long userId);

    @Query(value = "SELECT new org.fastcampus.user.application.dto.GetUserListResponseDto(u.name, u.profileImage)"
            +" FROM UserRelationEntity ur"
            +" INNER JOIN UserEntity u ON ur.followingUserId = u.id"
            +" WHERE ur.followerUserId = :userId" , nativeQuery = true)
    List<GetUserListResponseDto>getFollowerUserList(Long userId);
}
