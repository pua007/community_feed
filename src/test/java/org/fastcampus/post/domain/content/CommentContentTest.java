package org.fastcampus.post.domain.content;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.NullAndEmptySource;
import org.junit.jupiter.params.provider.ValueSource;

class CommentContentTest {

    @Test
    void givenContentLengthIsOk_whenCreateCommentContent_thenReturnTextContent(){
        //given
        String contentText = "this is a test content";

        //when
        CommentContent content = new CommentContent(contentText);

        //then
        assertEquals(contentText, content.getContentText());
    }

    @Test
    void givenContentLengthIsOver_whenCreateCommentContent_thenThrowError(){
        //given
        String contentText = "a".repeat(101);

        //when, then
        assertThrows(IllegalArgumentException.class, ()->new CommentContent(contentText));

    }

    @ParameterizedTest
    @NullAndEmptySource
    void givenContentLengthIsEmptyAndNull_whenCreateCommentContent_thenThrowError(String contentText){
        assertThrows(IllegalArgumentException.class, ()->new CommentContent(contentText));
    }

}
