package org.zerock.j07.board.entity;

import lombok.*;
import org.zerock.j07.common.entity.BaseEntity;

import javax.persistence.*;

@Entity // 테이블로 만들어줌
@Table(name = "tbl_favorite", indexes = @Index(name = "idx_board", columnList = "board_dno"))  // 테이블 명, 인덱스 지정
@Getter  // ReadOnly getter만 생성가능
@Builder  // 다수의 필드를 가질 경우 생성자 대신 빌더를 사용
@AllArgsConstructor  // 모든 필드값을 파라미터로 받는 생성자 생성
@NoArgsConstructor   // 파라미터가 없는 기본생성자를 생성
@ToString
public class Favorite extends BaseEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long fno;

    private boolean mark;

    private String actor;

    @ManyToOne
    private Board board;


}
