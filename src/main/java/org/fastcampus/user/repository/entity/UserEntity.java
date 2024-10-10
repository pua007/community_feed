package org.fastcampus.user.repository.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.fastcampus.common.domain.PositiveIntegerCounter;
import org.fastcampus.common.repository.entity.TimeBaseEntity;
import org.fastcampus.user.domain.User;
import org.fastcampus.user.domain.UserInfo;

@Entity
@Table(name = "community_user")
@NoArgsConstructor
@AllArgsConstructor
@Getter
public class UserEntity extends TimeBaseEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY) //디비에서 아이디를 생성해달라고하는 어노테이션, 스트레이트지 옆에 제네네리션 타입 아이텐티티는 마이에스큐엘에서 사용하는 오토인크리먼트를 사용헤달라는 요청임
    private Long id;
    private String name;
    private String profileImage;
    private Integer followerCount;
    private Integer followingCount;

//    @OneToMany(fetch = FetchType.EAGER)//실무에서는 잘사용하지않는다 메모리부족을 초래할수 있다
//    private List<PostEntity> posts;

    public UserEntity(User user){
        this.id = user.getId();
        this.name = user.getName();
        this.profileImage = user.getProfileImage();
        this.followingCount = user.getFollowingCount();
        this.followerCount = user.getFollowerCounter();
    }

    public User toUser(){
        return User.builder()
                .id(id)
                .info(new UserInfo(name, profileImage))
                .followerCounter(new PositiveIntegerCounter(followerCount))
                .followingCount(new PositiveIntegerCounter(followingCount))
                .build();
    }

}
