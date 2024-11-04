package org.fastcampus.user.application;

import java.util.IllformedLocaleException;
import org.fastcampus.user.application.dto.CreateUserRequestDto;
import org.fastcampus.user.application.interfaces.UserRepository;
import org.fastcampus.user.domain.User;
import org.fastcampus.user.domain.UserInfo;
import org.springframework.stereotype.Service;

//컨포넌트와 동일함
@Service
public class UserService {
    //다른 객체와 협업을하는 곳

    private final UserRepository userRepository;

    public UserService(UserRepository userRepository){
        this.userRepository = userRepository;
    }

    public User createUser(CreateUserRequestDto dto){
        UserInfo info = new UserInfo(dto.name(), dto.profileImageUrl());
        User user = new User(null, info);
        return userRepository.save(user);
    }


    public User getUser(Long id){
        return  userRepository.findById(id);
    }
}
