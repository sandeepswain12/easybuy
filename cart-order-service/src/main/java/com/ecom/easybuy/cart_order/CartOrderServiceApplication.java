package com.ecom.easybuy.cart_order;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.openfeign.EnableFeignClients;

@EnableFeignClients
@SpringBootApplication
public class CartOrderServiceApplication {

	public static void main(String[] args) {
		SpringApplication.run(CartOrderServiceApplication.class, args);
	}

}
