package org.fastcampus.common.repository.entity;

import jakarta.persistence.Column;
import jakarta.persistence.EntityListeners;
import jakarta.persistence.MappedSuperclass;
import lombok.Getter;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import java.time.LocalDateTime;

@EntityListeners(AuditingEntityListener.class)//하이버네이트가 디비에 어떤행위를 하는 시점이나 이후 행위를 할때 콜백해주는 어노테이션//거기에 기본적으로 JPA에서 제공을하는 AuditingEntityListener.class넣어서 생성될때 그리고 마지막으로 수정될때가 자동으로 칼럼에 데이터를 채워넣어주는 역할을 한다.
@MappedSuperclass//공통맵핑정보가필요할때 속성만 상속받아서 사용할 수있게 만들어주는 어노테이션
@Getter
public class TimeBaseEntity {
    @CreatedDate
    @Column(updatable = false)
    private LocalDateTime regDt;

    @LastModifiedDate
    private LocalDateTime updDt;
}
