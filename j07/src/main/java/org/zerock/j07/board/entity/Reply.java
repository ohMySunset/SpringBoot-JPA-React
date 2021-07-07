package org.zerock.j07.board.entity;

import lombok.*;
import org.hibernate.annotations.Fetch;
import org.zerock.j07.common.entity.BaseEntity;

import javax.persistence.*;

@Entity // 테이블로 만들어줌
@Table(name = "tbl_reply", indexes = @Index(name = "idx_board", columnList = "board_dno"))  // 테이블 명, 인덱스 지정
@Getter  // ReadOnly getter만 생성가능
@Builder
@AllArgsConstructor
@NoArgsConstructor
@ToString(exclude = "board")  // 연관관계가 맺어져 있는 board는 제외하고 출력하도록한다.
public class Reply extends BaseEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long rno;

    private String replyText;

    @JoinColumn
    @ManyToOne(fetch = FetchType.LAZY) // 객체 생성시 JPA 연관관계 설정  --> LAZY
    private Board board;

}
