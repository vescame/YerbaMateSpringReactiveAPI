package org.vescm.yerbamate.controller;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.vescm.yerbamate.document.YerbaMate;
import org.vescm.yerbamate.service.YerbaMateService;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@Slf4j
@RequiredArgsConstructor
@RestController
@RequestMapping("/api/v1/yerbas")
public class YerbaMateController {
    private final YerbaMateService yerbaMateService;

    @GetMapping
    @ResponseStatus(HttpStatus.OK)
    public Flux<YerbaMate> getAll() {
        log.info("Listing Yerba Mate");
        return yerbaMateService.findAll();
    }

    @GetMapping("/{id}")
    public Mono<ResponseEntity<YerbaMate>> findById(@PathVariable String id) {
        log.info("GET - yerba ID {}", id);
        return yerbaMateService.findById(id)
                .map((item) -> new ResponseEntity<>(item, HttpStatus.OK))
                .defaultIfEmpty(new ResponseEntity<>(HttpStatus.NOT_FOUND));
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public Mono<YerbaMate> create(@RequestBody YerbaMate yerbaMate) {
        log.info("POST - yerba {} created", yerbaMate.getName());
        return yerbaMateService.save(yerbaMate);
    }

    @DeleteMapping("/{id}")
    @ResponseStatus(code = HttpStatus.NOT_FOUND)
    public Mono<HttpStatus> deletebyIDYerbaMate(@PathVariable String id) {
        yerbaMateService.deletebyId(id);
        log.info("DELETE - the yerba ID: {}", id);
        return Mono.just(HttpStatus.NOT_FOUND);
    }
}
