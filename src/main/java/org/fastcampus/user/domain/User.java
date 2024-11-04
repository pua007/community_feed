package org.fastcampus.user.domain;

import jakarta.persistence.Embedded;
import jakarta.persistence.Entity;
import jakarta.persistence.ManyToOne;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.fastcampus.common.domain.PositiveIntegerCounter;
import org.springframework.data.annotation.Id;

import java.util.Objects;

@Getter
@Builder
@AllArgsConstructor
public class User {

    private Long id;

    private String name;

    private String profileImageUrl;

    private UserInfo info;

    private PositiveIntegerCounter followingCount;

    private PositiveIntegerCounter followerCounter;

    public User(Long id, UserInfo userInfo) {
        if (userInfo == null) {
            throw new IllegalArgumentException();
        }

        this.id = id;
        this.info = userInfo;
        this.name = userInfo.getName();
        this.profileImageUrl = userInfo.getProfileImageUrl();
        this.followingCount = new PositiveIntegerCounter();
        this.followerCounter = new PositiveIntegerCounter();

    }

    public void follow(User targetUser) {
        if (targetUser.equals(this)) {
            throw new IllegalArgumentException();
        }
        //나의 팔로잉 카운터를 올린다
        followingCount.increase();
        //타겟유저의 팔로카운트를 높이고
        targetUser.increaseFollowercount();
    }

    public void unfollow(User targetUser) {
        if (targetUser.equals(this)) {
            throw new IllegalArgumentException();
        }
        followingCount.decrease();
        targetUser.decreaseFollowercount();
    }

    private void increaseFollowercount() {
        followerCounter.increase();
    }

    private void decreaseFollowercount() {
        followerCounter.decrease();
    }



    @Override
    public boolean equals(Object o) {
        if (this == o)
            return true;
        if (o == null || getClass() != o.getClass())
            return false;
        User user = (User) o;
        return Objects.equals(id, user.id);
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(id);
    }

    public int  getFollowingCount() {

        return followingCount.getCount();
    }

    public int  getFollowerCounter() {

        return followerCounter.getCount();
    }

    public String getProfileImage(){
        return info.getProfileImageUrl();
    }

    public String getName(){
        return info.getName();
    }


}
