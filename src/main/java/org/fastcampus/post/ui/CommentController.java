package org.fastcampus.post.ui;

import lombok.RequiredArgsConstructor;
import org.fastcampus.common.ui.Response;
import org.fastcampus.post.appication.CommentService;
import org.fastcampus.post.appication.dto.CreateCommentRequestDto;
import org.fastcampus.post.appication.dto.LikeRequestDto;
import org.fastcampus.post.appication.dto.UpdateCommentRequestDto;
import org.fastcampus.post.domain.comment.Comment;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/comment")
@RequiredArgsConstructor
public class CommentController {

    private final CommentService commentService;

    @PostMapping
    public Response<Long> createComment (@RequestBody CreateCommentRequestDto dto){
        Comment comment = commentService.createComment(dto);
        return Response.ok(comment.getId());
    }

    @PostMapping("/{commnetId}")
    public Response<Long> updateCommnet(@PathVariable(name="commnetId")Long commentId, @RequestBody UpdateCommentRequestDto dto){
        Comment comment = commentService.updateComment(commentId, dto);
        return Response.ok(comment.getId());
    }

    @PostMapping("/like")
    public Response<Void> likeCount(@RequestBody LikeRequestDto dto){
        commentService.likeComment(dto);
        return Response.ok(null);
    }

    @PostMapping("/uplike")
    public Response<Void> unlikeCount(@RequestBody LikeRequestDto dto){
        commentService.unlikeComment(dto);
        return Response.ok(null);
    }
}
