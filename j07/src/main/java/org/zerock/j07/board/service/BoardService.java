package org.zerock.j07.board.service;

import org.zerock.j07.board.dto.BoardDTO;
import org.zerock.j07.board.dto.BoardRequestDTO;
import org.zerock.j07.board.dto.ListBoardDTO;
import org.zerock.j07.board.entity.Board;
import org.zerock.j07.common.dto.ListResponseDTO;

public interface BoardService {

    ListResponseDTO<ListBoardDTO> getList(BoardRequestDTO boardRequestDTO);

    default BoardDTO entityToDTO(Board board){

        return BoardDTO.builder()
                .dno(board.getDno())
                .title(board.getTitle())
                .content(board.getContent())
                .writer(board.getWriter())
                .modDate(board.getModDate())
                .regDate(board.getRegDate())
                .build();
    }

    default ListBoardDTO arrToDTO(Object[] arr){

        Board board = (Board)arr[0];
        long replyCount = (long)arr[1];
        long likeCount = (long)arr[2];

        return ListBoardDTO.builder()
                .boardDTO(entityToDTO(board))
                .replyCount(replyCount)
                .likeCount(likeCount)
                .build();
    }


}
