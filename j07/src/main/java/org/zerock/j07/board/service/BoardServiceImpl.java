package org.zerock.j07.board.service;

import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.zerock.j07.board.dto.BoardRequestDTO;
import org.zerock.j07.board.dto.ListBoardDTO;
import org.zerock.j07.board.entity.Board;
import org.zerock.j07.board.repository.BoardRepository;
import org.zerock.j07.common.dto.ListResponseDTO;
import org.zerock.j07.common.dto.PageMaker;
import org.zerock.j07.todo.dto.TodoDTO;

import java.util.List;
import java.util.stream.Collectors;

@Service
@Log4j2
@RequiredArgsConstructor
public class BoardServiceImpl implements BoardService {

    private final BoardRepository boardRepository;

    @Override
    public ListResponseDTO<ListBoardDTO> getList(BoardRequestDTO boardRequestDTO) {

        Pageable pageable = boardRequestDTO.getPageable();

        Page<Object[]> result = boardRepository.getSearchList(boardRequestDTO.getType(), boardRequestDTO.getKeyword(), pageable);

        List<ListBoardDTO> dtoList = result.getContent()
                .stream().map(list -> arrToDTO(list))
                .collect(Collectors.toList());

        PageMaker pageMaker = new PageMaker(boardRequestDTO.getPage(), boardRequestDTO.getSize(), (int) result.getTotalElements());

        //log.info(result);

        return ListResponseDTO.<ListBoardDTO>builder()
                .dtoList(dtoList)
                .pageMaker(pageMaker)
                .listRequestDTO(boardRequestDTO)
                .build();

    }
}
