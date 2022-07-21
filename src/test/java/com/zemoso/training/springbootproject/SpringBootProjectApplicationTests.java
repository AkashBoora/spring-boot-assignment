package com.zemoso.training.springbootproject;

import com.zemoso.training.springbootproject.controller.ShopController;
import org.junit.jupiter.api.Test;
import org.junit.runners.Suite;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;

@SpringBootTest
class SpringBootProjectApplicationTests {

	@Autowired
	ShopController shopController;


	@Test
	void contextLoads() {
		assertThat(shopController).isNotNull();
	}

}
