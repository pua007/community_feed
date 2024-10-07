package org.fastcampus.user.application;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.fastcampus.fake.FakeObjectFactory;
import org.fastcampus.user.application.dto.CreateUserRequestDto;
import org.fastcampus.user.application.interfaces.UserRepository;
import org.fastcampus.user.domain.User;
import org.fastcampus.user.domain.UserInfo;
import org.fastcampus.user.repository.FakeUserRepository;
import org.junit.jupiter.api.Test;

class UserServiceTest {

//    private UserRepository userRepository = new FakeUserRepository();
//    private final UserService userService = new UserService(userRepository);
    private final UserService userService = FakeObjectFactory.getUserService();

    @Test
    void givenUserInfoDto_whenCreateUser_thenCanFindUser(){
        //given
        CreateUserRequestDto dto = new CreateUserRequestDto("test","");
        //when
        User saveUser = userService.createUser(dto);
        //then
        User foundUser =userService.getUser(saveUser.getId());
        UserInfo userInfo = foundUser.getInfo();

        assertEquals(foundUser.getId(), saveUser.getId());
        assertEquals("test", userInfo.getName());

    }

}
