package com.univeristy.ms.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.context.request.RequestAttributes;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import feign.RequestInterceptor;
import feign.RequestTemplate;
import jakarta.servlet.http.HttpServletRequest;

@Configuration
public class FeignClientInterceptor {

    @Bean
    public RequestInterceptor requestInterceptor() {

        return new RequestInterceptor() {

            @Override
            public void apply(RequestTemplate template) {

                RequestAttributes requestAttributes =
                        RequestContextHolder.getRequestAttributes();

                if (requestAttributes instanceof ServletRequestAttributes) {

                    HttpServletRequest request =
                            ((ServletRequestAttributes) requestAttributes)
                                    .getRequest();

                    String authHeader =
                            request.getHeader("auth");

                    if (authHeader != null) {

                        template.header("auth", authHeader);
                    }
                }
            }
        };
    }
}