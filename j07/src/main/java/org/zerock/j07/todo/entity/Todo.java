package org.zerock.j07.todo.entity;

import lombok.*;
import org.zerock.j07.common.entity.BaseEntity;

import javax.persistence.*;

@Entity // 테이블로 만들어줌
@Table(name="tbl_todo")  // 테이블 명
@Getter  // ReadOnly getter만 생성가능
@Builder
@AllArgsConstructor
@NoArgsConstructor
@ToString
public class Todo extends BaseEntity {  // BaseEntity를 상속

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long tno;  // idx

    @Column(nullable = false, length = 300)  //Not Null, 길이 300
    private String content;  // 컨텐츠

    private boolean del; // 삭제여부

    // Setter의 역할
    public void changeTitle(String content){
        this.content = content;
    }

}
