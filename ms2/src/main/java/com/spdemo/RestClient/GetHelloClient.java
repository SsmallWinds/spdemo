package com.spdemo.RestClient;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;

@FeignClient(value = "spdemo-ms1", fallbackFactory = GetHelloFallBackFactory.class)
public interface GetHelloClient {

    @GetMapping("/hello")
    public String getHello();
}
