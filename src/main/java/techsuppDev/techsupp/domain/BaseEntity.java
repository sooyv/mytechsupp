package techsuppDev.techsupp.domain;


import lombok.Getter;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import javax.persistence.Column;
import javax.persistence.EntityListeners;
import javax.persistence.MappedSuperclass;
import java.time.LocalDateTime;


@MappedSuperclass // 테이블로 생성되지 않도록 해주는 어노테이션
@EntityListeners(value = {AuditingEntityListener.class})
// AuditingEntityListener : JPA 내부에서 엔티티 객체가 생성/변경 되는 것을 감지
@Getter
abstract class BaseEntity {
    @CreatedDate // Auditing에서 데이터가 생성해주길 바라는 변수
    @Column(name = "regdate", updatable = false)
    // updatable = false : 객체를 DB에 반영할 때 컬럼값은 변경되지 않는다.
    private LocalDateTime regDate;

    @LastModifiedDate // Auditing에서 데이터가 업데이트 해주길 바라는 변수
    @Column(name = "moddate")
    private LocalDateTime modDate;
}

