package com.spdemo.RestClient;

import feign.hystrix.FallbackFactory;
import org.springframework.stereotype.Component;

@Component
public class GetHelloFallBackFactory implements FallbackFactory<GetHelloClient> {
    @Override
    public GetHelloClient create(Throwable throwable) {
        return new GetHelloClient() {
            @Override
            public String getHello() {
                return "fall back：" + throwable.getMessage();
            }
        };
    }
}
