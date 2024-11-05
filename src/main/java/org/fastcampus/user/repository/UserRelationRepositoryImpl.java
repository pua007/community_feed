package org.fastcampus.user.repository;

import jakarta.transaction.Transactional;

import java.util.ArrayList;
import java.util.List;
import lombok.RequiredArgsConstructor;
import org.fastcampus.user.application.interfaces.UserRelationRepository;
import org.fastcampus.user.domain.User;
import org.fastcampus.user.repository.entity.UserEntity;
import org.fastcampus.user.repository.entity.UserRelationEntity;
import org.fastcampus.user.repository.entity.UserRelationIdEntity;
import org.fastcampus.user.repository.jpa.JpaUserRelationRepository;
import org.fastcampus.user.repository.jpa.JpaUserRepository;
import org.springframework.stereotype.Repository;

@Repository
@RequiredArgsConstructor
public class UserRelationRepositoryImpl implements UserRelationRepository {

    private final JpaUserRelationRepository jpaUserRelationRepository;
    private final JpaUserRepository jpaUserRepository;


    @Override
    public boolean isAlreadyFollow(User user, User targetUser) {
        UserRelationIdEntity id = new UserRelationIdEntity(user.getId(), targetUser.getId());
        return jpaUserRelationRepository.existsById(id);

    }

    @Override
    @Transactional
    public void save(User user, User targetUser) {
        UserRelationEntity entity = new UserRelationEntity(user.getId(), targetUser.getId());

        jpaUserRelationRepository.save(entity);

        // saveAll 메서드는 리스트의 각 엔티티가 같은 ID를 가지고 있으면 해당 엔티티를 업데이트합니다.
        // List.of를 사용하면 불변 리스트를 간단히 생성할 수 있어 코드가 간결해집니다.
        jpaUserRepository.saveAll(List.of(new UserEntity(user), new UserEntity(targetUser)));
        //.saveAll(): 만약에 같은 id의 값이 있으면 업데이트 해준다.
        //List.of를 빼도 세이브는 작동을 하나 리스트오브를 쓰지않고 작성하면
//        List<UserEntity> list = new ArrayList<>();
//        list.add(new UserEntity(user));
//        list.add(new UserEntity(targetUser)); 이런식으로 코드가 길어진다 여기서 saveAll(list);를 해도 상관은 없다
    }

    @Override
    @Transactional
    public void delete(User user, User targetUser) {
        UserRelationIdEntity id = new UserRelationIdEntity(user.getId(), targetUser.getId());
        jpaUserRelationRepository.deleteById(id);
        jpaUserRepository.saveAll(List.of(new UserEntity(user), new UserEntity(targetUser)));
    }
}
