package org.fastcampus.post.domain;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

import org.fastcampus.post.domain.content.PostContent;
import org.fastcampus.user.domain.User;
import org.fastcampus.user.domain.UserInfo;
import org.junit.jupiter.api.Test;

class PostTest {
    private final UserInfo info = new UserInfo("name", "url");
    private final User user = new User(1l, info);
    private final User otherUser = new User(2L, info);

    private final Post post = Post.createDeaultStatePost(1L, user, new PostContent("content"));

    @Test
    void givenPostCreated_whenLike_thenLikeCountsShouldBe1(){
        //when
        post.like(otherUser);

        //then
        assertEquals(1, post.getLikeCount());
    }

    @Test
    void givenPostCreated_whenLikeBySelf_thenThrowException(){
        //when, then
        assertThrows(IllegalArgumentException.class, ()->post.like(user));
    }

    @Test
    void givenPostCreatedAndLike_whenUnlike_thenLikeCountShouldBe0(){
        //given
        post.like(otherUser);

        //when
        post.unLike();

        //then
        assertEquals(0,post.getLikeCount());
    }

    @Test
    void givenPostCreated_whenUnlike_thenLikeCountShouldBe0(){
        //when
        post.unLike();

        //then
        assertEquals(0,post.getLikeCount());
    }

    @Test
    void givenPostCreated_whenUpdateContent_thenContentShouldBeUpdated(){
        //given
        String newPostContent = "new content";

        //when
        post.updatePost(user, newPostContent, PostPublicationState.PUBLIC);

        //then
        assertEquals(newPostContent, post.getContent());
    }

   @Test
    void givenPostCreated_whenUpdateOtherUserContent_thenThrowExeption(){
        //given
        String newPostContent = "new content";

       //when, then
       assertThrows(IllegalArgumentException.class,()-> post.updatePost(otherUser, newPostContent, PostPublicationState.PUBLIC));
   }

}
