package com.univeristy.ms.feignclients;

/**
 * If we are using api-gateway then no need of client side load balancing, 
 * because api-gateway itself handle it. 
 * 
 */

//import org.springframework.cloud.client.loadbalancer.LoadBalanced;
//import org.springframework.cloud.loadbalancer.annotation.LoadBalancerClient;
//import org.springframework.context.annotation.Bean;
//
//import feign.Feign;
//
//@LoadBalancerClient(value = "address-service")
//public class AddressServiceLoadBalanceConfig {
//	
//	@LoadBalanced
//	@Bean
//	public Feign.Builder feignBuilder(){
//		return Feign.builder();
//	}
//
//}
