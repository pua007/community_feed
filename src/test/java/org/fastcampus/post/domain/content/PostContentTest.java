package org.fastcampus.post.domain.content;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.NullAndEmptySource;
import org.junit.jupiter.params.provider.ValueSource;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

class PostContentTest {

    @Test
    void givenContentLengthIsOk_whenCreated_thenReturnTextContent(){
        //given
        String text = "this is a test";
        //when
        PostContent content = new PostContent(text);
        //then
        assertEquals(text, content.getContentText());
    }

    @Test
    void givenContentLengthIsOver_whenCreated_thenReturnThrowError(){
        //given
        String content = "뿱".repeat(501);

        //when, then
        assertThrows(IllegalArgumentException.class, () -> new PostContent(content));
    }

    @ParameterizedTest
    @ValueSource(strings = "뿱,닭,굶,숢")
    void givenContentLengthIsOverAndKorean_whenCreated_thenReturnThrowError(String koreanWord){
        //given
        String content = koreanWord.repeat(501);

        //when, then
        assertThrows(IllegalArgumentException.class, () -> new PostContent(content));
    }

    @Test
    void givenContentLengthIsUnder_whenCreated_thenReturnThrowError(){
        //given
        String content = "뿱".repeat(4);

        //when, then
        assertThrows(IllegalArgumentException.class, () -> new PostContent(content));
    }

    @ParameterizedTest
    @NullAndEmptySource
    void givenContentLengthIsEmpty_whenCreated_thenReturnThrowError(String value){
        //when, then
        assertThrows(IllegalArgumentException.class, () -> new PostContent(value));
    }

    @Test
    void givenContentLengthIsOk_whenUpdated_thenReturnContent(){
        //given
        String content = "this is test content";
        PostContent postContent = new PostContent(content);

        //when
        String updateContent = "this is updated content";
        postContent.updateContent(updateContent);

        //then
        assertEquals(updateContent, postContent.getContentText());
    }

    @Test
    void givenContentLengthIsOver_whenUpdated_thenThrowError(){
        //given
        String content = "this is a test content";
        PostContent postContent = new PostContent(content);

        //when, then
        String value = "a".repeat(501);
        assertThrows(IllegalArgumentException.class, ()->postContent.updateContent(value));
    }

    @ParameterizedTest
    @ValueSource(strings = "쀍, 닭, 떫, 붉")
    void givenContentLengthIsOverAndKorean_whenUpdated_thenThrowError(String koreanWord){
        //given
        String content = "this is a test content";
        PostContent postContent = new PostContent(content);

        //when, then
        String value = koreanWord.repeat(501);
        assertThrows(IllegalArgumentException.class, () -> postContent.updateContent(value));
    }

    @Test
    void givenContentLengthIsUnder_whenUpdated_thenThrowError(){
        //given
        String content = "this is a test content";
        PostContent postContent = new PostContent(content);

        //when, tehn
        String value = "a".repeat(4);
        assertThrows(IllegalArgumentException.class, ()->postContent.updateContent(value));
    }


}
