package org.fastcampus.user.application;

import java.util.IllformedLocaleException;
import org.fastcampus.user.application.dto.CreateUserRequestDto;
import org.fastcampus.user.application.interfaces.UserRepository;
import org.fastcampus.user.domain.User;
import org.fastcampus.user.domain.UserInfo;

public class UserService {
    //다른 객체와 협업을하는 곳

    private final UserRepository userRepository;

    public UserService(UserRepository userRepository){
        this.userRepository = userRepository;
    }

    public User createUser(CreateUserRequestDto dto){
        UserInfo info = new UserInfo(dto.name(), dto.profileImageUrsl());
        User user = new User(null, info);
        return userRepository.save(user);
    }


    public User getUser(Long id){
        return  userRepository.findById(id).orElseThrow(IllformedLocaleException::new);
    }
}