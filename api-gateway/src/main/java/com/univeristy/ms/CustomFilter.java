package com.univeristy.ms;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.cloud.gateway.filter.GatewayFilterChain;
import org.springframework.cloud.gateway.filter.GlobalFilter;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.Ordered;
import org.springframework.http.HttpStatus;
import org.springframework.http.server.reactive.ServerHttpRequest;
import org.springframework.http.server.reactive.ServerHttpResponse;
import org.springframework.web.server.ServerWebExchange;

import reactor.core.publisher.Mono;

@Configuration
public class CustomFilter implements GlobalFilter, Ordered {

	Logger logger = LoggerFactory.getLogger(CustomFilter.class);

	private static final String API_KEY = "my-secret-key";

	@Override
	public Mono<Void> filter(ServerWebExchange exchange, GatewayFilterChain chain) {
		ServerHttpRequest request = exchange.getRequest();

		String authHeader = request.getHeaders().getFirst("auth");

		logger.info("Authorization = {}", authHeader);

		if (authHeader == null || !authHeader.equals(API_KEY)) {

			exchange.getResponse().setStatusCode(HttpStatus.UNAUTHORIZED);

			return exchange.getResponse().setComplete();
		}

		//Pre filter -> consumer -> api gateway -> before going to micro-service operations
		//Post filter -> micro-service retuns response to api gateway -> before sending that response to consumer operations
		//then(Mono.fromRunnable(() -> { is for post filter, without that is pre filter
		return chain.filter(exchange).then(Mono.fromRunnable(() -> {
			ServerHttpResponse response = exchange.getResponse();
			 // Post Filter
            logger.info(
                    "Post Filter URL = {}, Status = {}",
                    request.getURI().getPath(),
                    response.getStatusCode());
		}));
		
	}

	@Override
	public int getOrder() {
		return -1;
	}

}
