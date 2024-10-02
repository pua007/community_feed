package org.fastcampus.post.repository;

import org.fastcampus.post.appication.interfaces.LikeRepository;
import org.fastcampus.post.domain.Post;
import org.fastcampus.post.domain.comment.Comment;
import org.fastcampus.user.domain.User;

import java.util.HashMap;
import java.util.Map;
import java.util.Set;

public class FakeLikeRepository implements LikeRepository {

    private Map<Post, Set<User>>  postLikes = new HashMap<>();
    private Map<Comment, Set<User>>  commentLikes = new HashMap<>();

    @Override
    public boolean checkLike(Post post, User user) {
        if(postLikes.get(post) == null){
            return false;
        }
        return postLikes.get(post).contains(user);
    }

    @Override
    public void like(Post post, User user) {
        Set<User> users = postLikes.get(post);
        if(users == null){
            users = Set.of(user);
        }else{
            users.add(user);
        }
        postLikes.put(post, users);

    }

    @Override
    public void unlike(Post post, User user) {

    }

    @Override
    public boolean checkLike(Comment comment, User user) {
        return false;
    }

    @Override
    public void like(Comment comment, User user) {

    }

    @Override
    public void unlike(Comment comment, User user) {

    }
}
