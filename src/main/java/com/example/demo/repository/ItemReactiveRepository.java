package com.example.demo.repository;

import org.springframework.data.mongodb.repository.ReactiveMongoRepository;

import com.example.demo.document.Item;

public interface ItemReactiveRepository extends ReactiveMongoRepository<Item, String> {

}
