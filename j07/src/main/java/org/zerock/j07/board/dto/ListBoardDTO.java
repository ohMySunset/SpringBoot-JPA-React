package org.zerock.j07.board.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class ListBoardDTO { // 게시글과 좋아요 수, 댓글 수를 가져올 리스트DTO

    private BoardDTO boardDTO;

    private long likeCount;

    private long replyCount;


}
