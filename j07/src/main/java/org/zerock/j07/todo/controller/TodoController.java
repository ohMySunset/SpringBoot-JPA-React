package org.zerock.j07.todo.controller;

import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.zerock.j07.common.dto.ListResponseDTO;
import org.zerock.j07.todo.dto.TodoDTO;
import org.zerock.j07.common.dto.ListRequestDTO;
import org.zerock.j07.todo.service.TodoService;

@RestController
@RequestMapping("/todos")
@RequiredArgsConstructor
@Log4j2
public class TodoController {

    private final TodoService todoService;

    @GetMapping("/list")
    public ResponseEntity<ListResponseDTO<TodoDTO>> list(ListRequestDTO dto) {

        return ResponseEntity.ok(todoService.list(dto));
    }

    @PostMapping("/register")
    public ResponseEntity<Long> register(@RequestBody TodoDTO dto) {

        log.info("register....." + dto);

        //Todo에 새 데이터 저장
        Long tno = todoService.register(dto);

        return ResponseEntity.ok().body(tno);
    }


    @GetMapping("/{tno}")
    public ResponseEntity<TodoDTO> read(@PathVariable Long tno) {

        TodoDTO dto = todoService.read(tno);

        return ResponseEntity.ok().body(dto);
    }

    @DeleteMapping("/{tno}")
    public ResponseEntity<Long> remove(@PathVariable("tno") Long tno) {

        Long deletedTno = todoService.remove(tno);

        return ResponseEntity.ok().body(deletedTno);
    }

    @PutMapping("/{tno}")
    public ResponseEntity<TodoDTO> modify(@PathVariable Long tno, @RequestBody TodoDTO dto) {

        dto.setTno(tno); // tno값 일치시키기 위해
        log.info(dto);

        TodoDTO result = todoService.modify(dto);

        return ResponseEntity.ok().body(result);
    }


}
