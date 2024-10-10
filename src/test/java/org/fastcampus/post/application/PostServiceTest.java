package org.fastcampus.post.application;

import org.fastcampus.post.appication.dto.LikeRequestDto;
import org.fastcampus.post.appication.dto.UpdatePostRequestDto;
import org.fastcampus.post.domain.Post;
import org.fastcampus.post.domain.PostPublicationState;
import org.junit.jupiter.api.Test;


import static org.junit.jupiter.api.Assertions.assertEquals;


class PostServiceTest extends PostApplicationTestTemplate{

    @Test
    void givenPostRequestDto_whenCreate_thenReturnPost(){
        //when
        Post savePost = postService.createPost(dto);

        //then
        Post post = postService.getPost(savePost.getId());
        assertEquals(savePost, post);

    }

    @Test
    void givenCreatePost_whenUpdate_thenReturnUpdatePost(){
        //given
        Post savePost = postService.createPost(dto);
        System.out.println(savePost.getContent());
        final UpdatePostRequestDto updateDto = new UpdatePostRequestDto(savePost.getId(), user.getId(),"this is update content", PostPublicationState.PUBLIC);


        //when
        Post updatePost = postService.updatePost(savePost.getId(), updateDto);
        //둘다 같은 객체를 참조하고 있으므로 updatepost가 업데이트 되면서 savePost 역시 같은 값으로 없데이트가 됨 .... 멍청이
        System.out.println(updatePost.getContent());
        System.out.println(savePost.getContent());

        //then
        assertEquals(savePost.getId(), updatePost.getId());
        assertEquals(savePost.getAuthor(), updatePost.getAuthor());
        assertEquals(savePost.getContent(), updatePost.getContent());
        assertEquals(savePost.getContent(), updatePost.getContent()); // 업데이트된 내용 확인 세이브 포스트 역시 업데이트 됨
        assertEquals("this is update content", updatePost.getContent()); // 업데이트된 내용 확인

    }

    @Test
    void givenCreatePost_whenLiked_thenReturnPostLikeCountIsOne(){
        //given
        Post savePost = postService.createPost(dto);

        //when
        postService.likePost(new LikeRequestDto(savePost.getId(), otherUser.getId()));

        //then
        assertEquals(1,savePost.getLikeCount());
    }

    @Test
    void givenCreatePost_whenUnlike_thenPostLikeCountIs0(){

        Post savePost = postService.createPost(dto);

        //when
        postService.likePost(new LikeRequestDto(savePost.getId(), otherUser.getId()));
        postService.unlikePost(new LikeRequestDto(savePost.getId(), otherUser.getId()));


        //then
        assertEquals(0,savePost.getLikeCount());

    }

    @Test
    void givenCreatePost_whenunLiked_thenReturnPostWithoutLike(){
        //given
        Post savePost = postService.createPost(dto);

        //when
        LikeRequestDto likeRequestDto = new LikeRequestDto(savePost.getId(), otherUser.getId());
        postService.unlikePost(likeRequestDto);

        //then
        assertEquals(0,savePost.getLikeCount());
    }

}
