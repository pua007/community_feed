package org.fastcampus.common.ui;

import org.fastcampus.common.domain.exception.ErrorCode;

public record Response<T>(Integer code, String message, T value) {

    //<T> 제네릭타입을 사용 할 것 이라고 선언하는 부분
    //Response<T> 메서드가 반활할 객체의 타입을 나타넴 이 메서드가 Response객체를 반활 할 것이며
    //그 객체가 T타입의 벨류를 포함할 것이라는 의미입니다.
    public static <T> Response<T> ok(T value){
        return new Response<>(0,"ok",value);
    }

    public static  <T> Response<T> error(ErrorCode error){
        return new Response<>(error.getCode(), error.getMessage(), null);
    }


}
