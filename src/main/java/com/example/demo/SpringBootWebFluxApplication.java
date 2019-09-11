package com.example.demo;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.example.demo.document.Item;
import com.example.demo.repository.ItemReactiveRepository;

import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@SpringBootApplication
@RestController
public class SpringBootWebFluxApplication {

	public static void main(String[] args) {
		SpringApplication.run(SpringBootWebFluxApplication.class, args);
	}

	@Autowired
	ItemReactiveRepository itemReactiveRepository;

	@GetMapping("hi")
	Flux<Item> getItem() {
		return itemReactiveRepository.findAll();
	}

	@GetMapping("hi2")
	Mono<Item> getItem1(@RequestParam("id3") String id) {
		return itemReactiveRepository.findById(id);
	}

}
