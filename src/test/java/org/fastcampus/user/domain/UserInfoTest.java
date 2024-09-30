package org.fastcampus.user.domain;

import org.fastcampus.common.domain.PositiveIntegerCounter;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

public class UserInfoTest {

    @Test
    void givenNameAndProfileImage_WhenCreated_ThenThrowNothing(){
        //given
        String name = "abcd";
        String profileImage = "";
        //when
        //then
        assertDoesNotThrow(() -> new UserInfo(name, profileImage));
    }

    @Test
    void givenBlankNameAndProfileImage_WhenCreated_ThenThrowError(){
        //given
        String name = "";
        String profileImage = "";
        //when
        //then
        assertThrows(IllegalArgumentException.class,() -> new UserInfo(name, profileImage));
    }




}
