package org.fastcampus.post.domain.comment;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

import jdk.jfr.Threshold;
import org.fastcampus.post.domain.Post;
import org.fastcampus.post.domain.content.CommentContent;
import org.fastcampus.post.domain.content.PostContent;
import org.fastcampus.user.domain.User;
import org.fastcampus.user.domain.UserInfo;
import org.junit.jupiter.api.Test;

class CommentTest {

    private final UserInfo info = new UserInfo("name", "url");
    private final User user = new User(1l, info);
    private final User otherUser = new User(2L, info);

    private final Post post = new Post(1L, user, new PostContent("content"));
    private Comment comment = new Comment(user.getId(), post, user, new CommentContent("content"));

    @Test
    void givenCommentCreated_whenLike_thenLikeCountShouldBe1(){
        //when
        comment.like(otherUser);

        //then
        assertEquals(1, comment.getLikeCount());
    }

    @Test
    void givenCommentCreate_whenLikeBySelf_thenThrowException(){
        //when, then
        assertThrows(IllegalArgumentException.class, ()->comment.like(user));
    }

    @Test
    void givenCommentCreateAndLike_whenUnlike_thenLikeCountShouldBe0(){
        //given
        comment.like(otherUser);

        //when
        comment.unLike();

        //then
        assertEquals(0,comment.getLikeCount());
    }

    @Test
    void givenCommentCreate_whenUnlike_thenLikeCountShouldBe0(){
        //when
        comment.unLike();

        //then
        assertEquals(0,comment.getLikeCount());
    }

    @Test
    void givenCommentCreate_whenUpdateContent_thenContentShouldBeUpdated(){
        //given
        String newCommentContent = "new comment";

        //when
        comment.updateContent(user, newCommentContent);

        //then
        assertEquals(newCommentContent, comment.getContent());
    }

    @Test
    void givenComment_whenUpdateContentOver100_thenThrowException(){
        //given
        String newCommentContent = "a".repeat(101);

        //when, then
        assertThrows(IllegalArgumentException.class, ()->comment.updateContent(user, newCommentContent));
    }

}
