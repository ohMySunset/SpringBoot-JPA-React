package org.zerock.j09.controller;

import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.zerock.j09.dto.FoodStoreDTO;
import org.zerock.j09.dto.ListFoodStoreDTO;
import org.zerock.j09.service.FoodStoreService;

import java.util.List;

@RestController
@RequestMapping("/stores")
@Log4j2
@RequiredArgsConstructor
public class FoodStoreController {

    private final FoodStoreService foodStoreService;

    @PostMapping("/")
    public ResponseEntity<Long> register(@RequestBody FoodStoreDTO storeDTO){ //@RequestBody json타입으로 파라미터 수집

        Long fno = foodStoreService.register(storeDTO);

        return ResponseEntity.ok(fno);

    }

    @GetMapping("/list")
    public ResponseEntity<List<ListFoodStoreDTO>> getList(){

        return ResponseEntity.ok(foodStoreService.getList());
    }
}
