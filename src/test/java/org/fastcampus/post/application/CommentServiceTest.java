package org.fastcampus.post.application;

import org.fastcampus.post.appication.CommentService;
import org.fastcampus.post.appication.dto.LikeRequestDto;
import org.fastcampus.post.appication.dto.UpdateCommentRequestDto;
import org.fastcampus.post.domain.comment.Comment;
import org.fastcampus.post.domain.content.Content;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotEquals;

public class CommentServiceTest extends PostApplicationTestTemplate {

    @Test
    void givenCreateCommentRequestDto_whenCreateComment_thenReturnComment(){
        //when
        Comment comment = commentService.createComment(commentDto);

        //tnen
        String content = comment.getContent();
        assertEquals(commentContext, comment.getContent());
    }

    @Test
    void givenCreateComment_whenUpdateComment_thenUpdatedComment(){
        //given
        Comment comment = commentService.createComment(commentDto);

        //when
        UpdateCommentRequestDto updateCommentRequestDto = new UpdateCommentRequestDto( user.getId(),"this is update comment");
        Comment updateComment = commentService.updateComment(comment.getId(), updateCommentRequestDto);

        //then
        assertEquals(comment.getId(), updateComment.getId());
        assertEquals(comment.getAuthor(), updateComment.getAuthor());
        assertNotEquals(commentContext, updateComment.getContent());
        assertEquals(updateCommentRequestDto.content(), updateComment.getContent());
    }

    @Test
    void givenCreateComment_whenLike_thenCommentLikeCountIsOne(){
        //given
        Comment comment = commentService.createComment(commentDto);

        //when
        LikeRequestDto likeRequestDto = new LikeRequestDto(comment.getId(), otherUser.getId());
        commentService.likeComment(likeRequestDto);

        //then
        assertEquals(1, comment.getLikeCount());
    }

    @Test
    void givenCreareComment_whenUnLike_thenCommentLikeCountIs0(){
        //given
        Comment comment = commentService.createComment(commentDto);

        //when
        LikeRequestDto likeRequestDto = new LikeRequestDto(comment.getId(), otherUser.getId());
        commentService.likeComment(likeRequestDto);
        commentService.unlikeComment(likeRequestDto);

        //then
        assertEquals(0, comment.getLikeCount());
    }
}
