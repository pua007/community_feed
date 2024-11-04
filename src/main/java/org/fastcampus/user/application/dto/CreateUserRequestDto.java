package org.fastcampus.user.application.dto;

public record CreateUserRequestDto(String name, String profileImageUrl) {

    //자바14 지원되는 레코드객체 final 불변수로만 구성이 되어있고 겟터만 가진객체를들 변경이 가능
    //toString, equals, hashcode 자동생성됨

}
