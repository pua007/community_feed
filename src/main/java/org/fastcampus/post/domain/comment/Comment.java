package org.fastcampus.post.domain.comment;

import com.sun.source.tree.IfTree;
import org.fastcampus.common.domain.PositiveIntegerCounter;
import org.fastcampus.post.domain.Post;
import org.fastcampus.post.domain.content.CommentContent;
import org.fastcampus.post.domain.content.Content;
import org.fastcampus.post.domain.content.PostContent;
import org.fastcampus.user.domain.User;

public class Comment {

    private final Long id;
    private final Post post;
    private final User author;
    private final Content content;
    private final PositiveIntegerCounter likeCount;

    public static Comment createComment(Post post, User author, String content){
        return new Comment(null, post, author, new CommentContent(content));

    }

    public Comment(Long id, Post post, User author, Content content) {
        if(author == null){
            throw new IllegalArgumentException();
        }
        if(post == null){
            throw new IllegalArgumentException();
        }
        if(content == null){
            throw new IllegalArgumentException();
        }

        this.id = id;
        this.post = post;
        this.author = author;
        this.content = content;
        this.likeCount = new PositiveIntegerCounter();
    }

    public void like(User user){
        if(this.author.equals(user)){
            throw new IllegalArgumentException();
        }
        likeCount.increase();
    }

    public void unLike(){
        this.likeCount.decrease();
    }

    public void updateContent(User user, String updateContent){
        if(!this.author.equals(user)){
            throw new IllegalArgumentException();
        }

        this.content.updateContent(updateContent);
    }

    public int getLikeCount() {
        return likeCount.getCount();
    }

    public String getContent() {
        return content.getContentText();
    }
}
