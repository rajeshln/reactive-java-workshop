package io.javabrains.reactiveworkshop;

import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;
import reactor.core.scheduler.Schedulers;
import reactor.util.function.Tuple2;

import java.time.Duration;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

public class ReactorExamples {

    public static void main(String[] args) {

        // Creating Flux
        Flux<Integer> justFlux = Flux.just(1, 2, 3, 4, 5);
        List<String> list = Arrays.asList("A", "B", "C");
        Flux<String> fromIterableFlux = Flux.fromIterable(list);
        Flux<Integer> rangeFlux = Flux.range(1, 5);
        Flux<Object> emptyFlux = Flux.empty();
        Flux<Object> errorFlux = Flux.error(new RuntimeException("Something went wrong"));

        // Transforming Flux
        Flux<String> mapFlux = justFlux.map(i -> "Number: " + i);
        Flux<String> flatMapFlux = Flux.just("apple", "banana", "orange")
                .flatMap(fruit -> Flux.fromArray(fruit.split("")));
        Flux<Integer> filterFlux = rangeFlux.filter(i -> i % 2 == 0);
        Flux<String> concatMapFlux = Flux.just("apple", "banana", "orange")
                .concatMap(fruit -> Flux.fromArray(fruit.split("")));

        // Combining Flux
        Flux<Integer> flux1 = Flux.just(1, 2, 3);
        Flux<Integer> flux2 = Flux.just(4, 5, 6);
        Flux<Integer> mergedFlux = Flux.merge(flux1, flux2);
        Flux<Integer> concatenatedFlux = Flux.concat(flux1, flux2);
        Flux<Tuple2<Integer, String>> zippedFlux = Flux.zip(flux1, Flux.just("A", "B", "C"));

        // Handling Errors
        Flux<Integer> errorHandlingFlux = justFlux.concatWith(Flux.error(new RuntimeException("Error occurred")))
                .onErrorResume(e -> Flux.just(-1, -2));
        Flux<Integer> errorReturnFlux = justFlux.concatWith(Flux.error(new RuntimeException("Error occurred")))
                .onErrorReturn(-1);
        Flux<Integer> doOnErrorFlux = justFlux.concatWith(Flux.error(new RuntimeException("Error occurred")))
                .doOnError(e -> System.err.println("Error: " + e.getMessage()));

        // Aggregating Operations
        Mono<Integer> sumMono = justFlux.reduce((x, y) -> x + y);
        Mono<List<Integer>> listMono = justFlux.collectList();
        Mono<Long> countMono = justFlux.count();

        // Filtering and Distinct
        Flux<Integer> distinctFlux = Flux.just(1, 2, 2, 3, 3, 4, 5).distinct();
        Flux<Integer> distinctUntilChangedFlux = Flux.just(1, 1, 2, 3, 3, 4, 5).distinctUntilChanged();

        // Collecting values into a List
        Flux<Integer> integerFlux = Flux.just(1, 2, 3, 4, 5);
        Mono<List<Integer>> listMono1 = integerFlux.collectList();
        List<Integer> collect = Flux.just(1, 1, 2, 3, 3, 4, 5).distinctUntilChanged().toStream().collect(Collectors.toList());


    /* In a truly reactive system, you typically want to avoid blocking operations, as they can negate the benefits of
    using a reactive paradigm. However, in certain scenarios where blocking is acceptable or necessary (e.g., in a non-reactive
    context or for certain batch processing tasks), this approach might be suitable.*/

        Flux<Integer> flux = Flux.just(1, 1, 2, 3, 3, 4, 5)
                .distinctUntilChanged();

        flux.collectList().subscribe(collect1 -> {
            System.out.println(collect1);
        });

        Flux<Integer> takeFlux = justFlux.take(3);

        Flux<Integer> takeLastFlux = justFlux.takeLast(2);

        // Time-Related Operations
        Flux<Integer> delayElementsFlux = justFlux.delayElements(Duration.ofSeconds(1));
        Flux<Long> intervalFlux = Flux.interval(Duration.ofSeconds(1)).take(5);
        Flux<Integer> timeoutFlux = justFlux.concatWith(Flux.just(4).delayElements(Duration.ofSeconds(3)))
                .timeout(Duration.ofSeconds(2));

        // Concurrency Control
  /*      Flux<Integer> publishOnSubscribeOnFlux = justFlux.publishOn(ioProjectReactor())
                .map(i -> i * 2)
                .subscribeOn(Schedulers.single());*/

        Flux<Integer> parallelFlux = justFlux.parallel()
                .runOn(Schedulers.parallel())
                .map(i -> i * 2)
                .sequential();

        // Print the results or subscribe to the Flux or Mono as needed
        distinctUntilChangedFlux.subscribe(System.out::println);
    }
}
