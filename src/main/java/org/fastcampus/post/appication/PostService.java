package org.fastcampus.post.appication;

import lombok.RequiredArgsConstructor;
import org.fastcampus.post.appication.dto.CreatePostRequestDto;
import org.fastcampus.post.appication.dto.LikeRequestDto;
import org.fastcampus.post.appication.dto.UpdatePostRequestDto;
import org.fastcampus.post.appication.interfaces.LikeRepository;
import org.fastcampus.post.appication.interfaces.PostRepository;
import org.fastcampus.post.domain.Post;
import org.fastcampus.user.application.UserService;
import org.fastcampus.user.domain.User;
import org.springframework.stereotype.Service;


@Service
public class PostService {

    private final UserService userService;

    private final LikeRepository likeRepository;

    private final PostRepository postRepository;

    public PostService(UserService userService, PostRepository postRepository, LikeRepository likeRepository) {
        this.postRepository = postRepository;
        this.userService = userService;
        this.likeRepository = likeRepository;
    }

    public Post getPost(Long id){
        return postRepository.findById(id);
    }

    public Post createPost(CreatePostRequestDto dto){
        System.out.println(postRepository.getClass().getName());
        System.out.println("5");
        User author = userService.getUser(dto.userId());
        System.out.println("6");
        Post post = Post.createPost(null, author, dto.content(), dto.state());
        System.out.println("7");
        return postRepository.save(post);
    }

    public Post updatePost(Long postId,UpdatePostRequestDto dto){
        Post post = getPost(postId);
        User user = userService.getUser(dto.userId());

        post.updatePost(user, dto.content(),dto.state());
        return postRepository.save(post);
    }

    public void likePost(LikeRequestDto dto){
        Post post = getPost(dto.targetId());
        User user = userService.getUser(dto.userId());

        if(likeRepository.checkLike(post, user)){
            return;
        }

        post.like(user);

        likeRepository.like(post, user);
    }

    public void unlikePost(LikeRequestDto dto){
        Post post = getPost(dto.targetId());
        User user = userService.getUser(dto.userId());

        if(likeRepository.checkLike(post, user)){
            post.unLike();
            likeRepository.unlike(post, user);
        }
    }


}
