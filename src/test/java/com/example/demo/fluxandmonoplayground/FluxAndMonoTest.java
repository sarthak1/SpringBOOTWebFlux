package com.example.demo.fluxandmonoplayground;

import org.junit.jupiter.api.Test;

import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;
import reactor.test.StepVerifier;

public class FluxAndMonoTest {

	@Test
	public void fluxTest() {
		Flux<String> strFlux = Flux.just("Spring", "Spring Boot", "Reactive Spring")
				// .concatWith(Flux.error(new RuntimeException("Exception Occurred")))
				.concatWith(Flux.just("After Error")).log();
		strFlux.subscribe(System.out::println, e -> System.err.println("Exception is ::" + e),
				() -> System.out.println("Completed"));

	}

	@Test
	public void fluxTestElements_WithoutError() {
		Flux<String> strFlux = Flux.just("Spring", "Spring Boot", "Reactive Spring")
				.concatWith(Flux.error(new RuntimeException("Exception Occurred"))).log();
		StepVerifier.create(strFlux).expectNext("Spring").expectNext("Spring Boot").expectNext("Reactive Spring")
				// .expectError(RuntimeException.class)
				.expectErrorMessage("Exception Occurred").verify();

	}

	@Test
	public void fluxTestElementsCount_WithoutError() {
		Flux<String> strFlux = Flux.just("Spring", "Spring Boot", "Reactive Spring")
				.concatWith(Flux.error(new RuntimeException("Exception Occurred"))).log();
		StepVerifier.create(strFlux).expectNext("Spring", "Spring Boot", "Reactive Spring")
				.expectErrorMessage("Exception Occurred").verify();

	}

	@Test
	public void monoTest() {
		Mono<String> strMono = Mono.just("Spring");
		StepVerifier.create(strMono.log()).expectNext("Spring").verifyComplete();

	}

	@Test
	public void monoTest_Error() {
		StepVerifier.create(Mono.error(new RuntimeException("Exception Occurred")).log()).expectError(RuntimeException.class)
				.verify();

	}

}
