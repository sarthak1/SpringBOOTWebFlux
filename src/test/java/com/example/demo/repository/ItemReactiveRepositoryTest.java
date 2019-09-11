package com.example.demo.repository;

import java.util.Arrays;
import java.util.List;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.data.mongo.DataMongoTest;
import org.springframework.test.context.junit4.SpringRunner;

import com.example.demo.document.Item;

import reactor.core.publisher.Flux;
import reactor.test.StepVerifier;

@DataMongoTest
@RunWith(SpringRunner.class)
public class ItemReactiveRepositoryTest {
	@Autowired
	ItemReactiveRepository itemReactiveRepository;

	List<Item> itemList = Arrays.asList(new Item(null, "Samsung TV", 400.0), new Item(null, "LG TV", 420.0),
			new Item(null, "Apple Watch", 299.99), new Item(null, "Beats Headphone", 149.99), new Item("ABC", "Bose Headphone", 149.99));

	@BeforeEach
	public void setUp() {
		itemReactiveRepository.deleteAll().thenMany(Flux.fromIterable(itemList)).flatMap(itemReactiveRepository::save)
				.doOnNext(item -> System.out.println("Inserted Item is:" + item)).blockLast();
	}

	@Test
	public void getAllItems() {
		StepVerifier.create(itemReactiveRepository.findAll()).expectSubscription().expectNextCount(5).verifyComplete();
	}
	
	@Test
	public void getItemByID() {
		StepVerifier.create(itemReactiveRepository.findById("ABC")).expectSubscription().expectNextMatches(item->item.getDescription().equals("Bose Headphone")).verifyComplete();
	}


}
