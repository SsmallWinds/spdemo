package com.spdemo.RestClient;


import org.springframework.stereotype.Component;

@Component
public class GetHelloFallBackClient implements GetHelloClient {
    @Override
    public String getHello() {
        return "fall back!";
    }
}
