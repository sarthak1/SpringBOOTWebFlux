package com.example.demo;

import java.util.Arrays;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

import com.example.demo.document.Item;
import com.example.demo.repository.ItemReactiveRepository;

import reactor.core.publisher.Flux;

@Component
public class ItemDataInitializer implements CommandLineRunner {
	@Autowired
	ItemReactiveRepository itemReactiveRepository;

	@Override
	public void run(String... args) throws Exception {
		initialDataSetup();

	}

	public List<Item> data() {
		return Arrays.asList(new Item(null, "Samsung TV", 399.99), new Item(null, "LG TV", 329.99),
				new Item(null, "Apple Watch", 349.99), new Item("ABC", "Beats HeadPhones", 19.999));

	}

	private void initialDataSetup() {
		itemReactiveRepository.deleteAll().thenMany(Flux.fromIterable(data())).flatMap(itemReactiveRepository::save)
				.thenMany(itemReactiveRepository.findAll())
				.subscribe(item -> System.out.println("Item inserted from CommandLineRunner:" + item));

	}

}
