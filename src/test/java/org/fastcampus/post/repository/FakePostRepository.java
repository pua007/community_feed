package org.fastcampus.post.repository;

import org.fastcampus.post.appication.interfaces.PostRepository;
import org.fastcampus.post.domain.Post;

import java.util.HashMap;
import java.util.Map;
import java.util.Optional;

public class FakePostRepository implements PostRepository {
    private final Map<Long, Post> store = new HashMap<>();

    @Override
    public Post save(Post post) {
        if(post.getId() != null){
            store.put(post.getId(), post);
            return post;
        }
        long id = store.size() + 1;
        Post newPost = Post.createDeaultStatePost(id,post.getAuthor(),post.getContentObject());
        store.put(id, newPost);
        return newPost;
    }

    @Override
    public Optional<Post> findById(Long Id) {
        return Optional.ofNullable(store.get(Id));
    }
}
