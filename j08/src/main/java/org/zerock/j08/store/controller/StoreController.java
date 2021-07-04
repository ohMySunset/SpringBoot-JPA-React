package org.zerock.j08.store.controller;

import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.zerock.j08.common.dto.ListResponseDTO;
import org.zerock.j08.store.dto.ListStoreDTO;
import org.zerock.j08.store.dto.RequestStoreDTO;
import org.zerock.j08.store.service.StoreService;

@RestController
@RequestMapping("/stores")
@Log4j2
@RequiredArgsConstructor
public class StoreController {

    private final StoreService storeService;

    @GetMapping("/list")
    public ResponseEntity<ListResponseDTO<ListStoreDTO>> getList(RequestStoreDTO requestStoreDTO){

        return  ResponseEntity.ok(storeService.getList(requestStoreDTO));
    }
}
