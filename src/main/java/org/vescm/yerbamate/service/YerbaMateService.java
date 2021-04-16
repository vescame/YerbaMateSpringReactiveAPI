package org.vescm.yerbamate.service;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.vescm.yerbamate.document.YerbaMate;
import org.vescm.yerbamate.repository.YerbaMateRepository;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@Service
@RequiredArgsConstructor
public class YerbaMateService {
    private final YerbaMateRepository yerbaMateRepository;

    public Mono<YerbaMate> findById(String id) {

        return Mono.justOrEmpty(this.yerbaMateRepository.findById(id));
    }

    public Flux<YerbaMate> findAll() {
        return Flux.fromIterable(this.yerbaMateRepository.findAll());
    }

    public Mono<YerbaMate> save(YerbaMate yerba) {
        return Mono.justOrEmpty(this.yerbaMateRepository.save(yerba));
    }

    public Mono<Boolean> deletebyId(String id) {
        yerbaMateRepository.deleteById(id);
        return Mono.just(true);
    }
}
