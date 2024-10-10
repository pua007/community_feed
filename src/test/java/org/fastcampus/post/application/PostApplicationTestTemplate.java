package org.fastcampus.post.application;

import org.fastcampus.fake.FakeObjectFactory;
import org.fastcampus.post.appication.CommentService;
import org.fastcampus.post.appication.PostService;
import org.fastcampus.post.appication.dto.CreateCommentRequestDto;
import org.fastcampus.post.appication.dto.CreatePostRequestDto;
import org.fastcampus.post.domain.Post;
import org.fastcampus.post.domain.PostPublicationState;
import org.fastcampus.user.application.UserService;
import org.fastcampus.user.application.dto.CreateUserRequestDto;
import org.fastcampus.user.domain.User;

public class PostApplicationTestTemplate {
    final UserService userService = FakeObjectFactory.getUserService();
    final PostService postService = FakeObjectFactory.getPostService();
    final CommentService commentService = FakeObjectFactory.commentService();

    final User user = userService.createUser(new CreateUserRequestDto("user1",null));
    final User otherUser = userService.createUser(new CreateUserRequestDto("user1", null));

    final CreatePostRequestDto dto = new CreatePostRequestDto(user.getId(), "this is test content", PostPublicationState.PUBLIC);
    final Post post = postService.createPost(dto);
    final CreateCommentRequestDto commentDto = new CreateCommentRequestDto(post.getId(), user.getId(), "this is test comment");
    final String commentContext = commentDto.content();


}
