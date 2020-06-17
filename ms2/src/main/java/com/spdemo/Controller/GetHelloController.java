package com.spdemo.Controller;

import com.github.benmanes.caffeine.cache.Cache;
import com.netflix.hystrix.contrib.javanica.annotation.HystrixCommand;
import com.spdemo.Cache.CacheServiceImpl;
import com.spdemo.Cache.Closure;
import com.spdemo.RestClient.GetHelloClient;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.cloud.client.loadbalancer.LoadBalanced;
import org.springframework.context.annotation.Bean;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

import java.text.SimpleDateFormat;
import java.util.Date;

@RestController
public class GetHelloController {

    @Autowired
    CacheServiceImpl cacheImpl;

    @Bean
    @LoadBalanced
    private RestTemplate getRestTemplate() {
        return new RestTemplate();
    }

    @Autowired
    private RestTemplate restTemplate;

    @Autowired
    private GetHelloClient getHelloClient;

    @GetMapping("getHello")
    @HystrixCommand(fallbackMethod = "defaultGetHello")
    String getHello() {
        return restTemplate.getForObject("http://spdemo-ms1/hello", String.class);
    }

    public String defaultGetHello() {
        return "fail";
    }

    @GetMapping("getHello2")
    String getHello2() {
        String key = "getHello2";
        String val = cacheImpl.getCache(key, new Closure<String, String>() {
            @Override
            public String execute(String input) {
                return getHelloClient.getHello() + ":" + new SimpleDateFormat("HH:mm:ss").format(new Date());
            }
        });

        return val;
    }
}
