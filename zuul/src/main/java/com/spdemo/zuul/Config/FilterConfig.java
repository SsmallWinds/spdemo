package com.spdemo.zuul.Config;

import com.spdemo.zuul.Filter.ErrorFilter;
import com.spdemo.zuul.Filter.IpFilter;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class FilterConfig {

    @Bean
    public IpFilter ipFilter(){
        return new IpFilter();
    }

    @Bean
    public ErrorFilter errorFilter(){
        return new ErrorFilter();
    }
}
