package org.fastcampus.user.domain;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertNotEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

class UserTest {

    private final UserInfo userInfo = new UserInfo("test", "");
    private User user1;
    private  User user2;

    @BeforeEach
    void init(){
        user1 = new User(1L, userInfo);
        user2 = new User(2L, userInfo);
    }

    @Test
    void givenTeoUser_whenEqual_thenReturnFalse(){
        //when
        boolean isSame = user1.equals(user2);
        //then
        assertFalse(isSame);
    }

    @Test
    void givenSameUser_whenEqual_thenReturnTrue(){

        //given
        User sameUser = new User(1L,userInfo);
        //when
        boolean isSame = user1.equals(sameUser);
        //then
        assertTrue(isSame);
    }

    @Test
    void givenTwoUser_whenHashCode_thenReturnNotEqual(){
        //given
        int hashCode1 = user1.hashCode();
        int hashCode2 = user2.hashCode();

        //then
        assertNotEquals(hashCode1,hashCode2);

    }

    @Test
    void givenSameUser_whenEqual_thenReturnEqual(){

        //given
        User sameUser = new User(1L,userInfo);
        //when
        int hashcode1 = user1.hashCode();
        int hashcode2 = sameUser.hashCode();
        //then
        assertEquals(hashcode1, hashcode2);
    }

    @Test
    void givenTowUser_whenUser1FollowUser2_thenIncreaseUserCount() {
        //when
        user1.follow(user2);

        //then
        assertEquals(1, user1.getFollowingCount());
        assertEquals(0, user1.getFollowerCounter());
        assertEquals(0, user2.getFollowingCount());
        assertEquals(1, user2.getFollowerCounter());
    }

    @Test
    void givenTowUserUser1FollowUser2_whenUser1UnfollowUser2_thenDecreaseUserCount() {
        //when
        user1.follow(user2);
        user1.unfollow(user2);

        //then
        assertEquals(0, user1.getFollowingCount());
        assertEquals(0, user1.getFollowerCounter());
        assertEquals(0, user2.getFollowingCount());
        assertEquals(0, user2.getFollowerCounter());
    }

    @Test
    void givenTowUser_whenUnfollowUser2_thenDecreaseUserCount(){
        //when
        user1.unfollow(user2);

        assertEquals(0, user1.getFollowingCount());
        assertEquals(0, user1.getFollowerCounter());
        assertEquals(0, user2.getFollowingCount());
        assertEquals(0, user2.getFollowerCounter());
    }
}
