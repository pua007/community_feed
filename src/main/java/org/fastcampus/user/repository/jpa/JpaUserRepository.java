package org.fastcampus.user.repository.jpa;

import org.fastcampus.user.repository.entity.UserEntity;
import org.springframework.data.jpa.repository.JpaRepository;
//JpaRepository<가지고와야하는 대상의 엔티티, 그 엔티티의 아이디값>
public interface JpaUserRepository extends JpaRepository<UserEntity, Long> {
}
