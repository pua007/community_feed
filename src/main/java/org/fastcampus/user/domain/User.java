package org.fastcampus.user.domain;

import org.fastcampus.common.domain.PositiveIntegerCounter;

import java.util.Objects;

public class User {

    private final Long id;
    private final UserInfo info;
    private final PositiveIntegerCounter followingCount;
    private final PositiveIntegerCounter followerCounter;

    public User(Long id, UserInfo userInfo) {
        if (userInfo == null) {
            throw new IllegalArgumentException();
        }

        this.id = id;
        this.info = userInfo;
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

    public UserInfo getInfo() {
        return info;
    }

    public int  getFollowingCount() {
        return followingCount.getCount();
    }

    public int  getFollowerCounter() {
        return followerCounter.getCount();
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

    public Long getId() {
        return id;
    }

}
