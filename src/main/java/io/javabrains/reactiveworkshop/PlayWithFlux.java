package io.javabrains.reactiveworkshop;

import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

public class PlayWithFlux {

    public static void main(String[] args) {

        Flux.just(1, 2, 3, 4)
                //  .delayElements(Duration.ofSeconds(1))
                .subscribe(System.out::println);

        Flux.range(1, 22).subscribe(System.out::print);
        System.out.println();
        Mono.just(1000).subscribe(System.out::print);
        Mono.never().subscribe(System.out::print);
        ;

    }
}
