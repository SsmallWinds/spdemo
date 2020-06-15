package com.spdemo.Controller;

import com.netflix.hystrix.contrib.javanica.annotation.HystrixCommand;
import com.spdemo.RestClient.GetHelloClient;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cloud.client.loadbalancer.LoadBalanced;
import org.springframework.context.annotation.Bean;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

@RestController
public class GetHelloController {

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

    public String defaultGetHello(){
        return "fail";
    }

    @GetMapping("getHello2")
    String getHello2() {
        return getHelloClient.getHello();
    }
}
