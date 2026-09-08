package com.pasarela.infrastructure.enrtrypoint;

import lombok.RequiredArgsConstructor;
import org.springframework.data.redis.core.ReactiveStringRedisTemplate;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;
import reactor.core.publisher.Mono;

@RestController
@RequiredArgsConstructor
public class RedisTestController {

    private final ReactiveStringRedisTemplate redisTemplate;

    @GetMapping("/redis-test")
    public Mono<String> test() {
        return redisTemplate.opsForValue()
                .set("test", "hola-valkey")
                .then(redisTemplate.opsForValue().get("test"));
    }
}