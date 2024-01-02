package io.javabrains.reactiveworkshop;

import java.io.IOException;

public class Exercise9 {

    // converting flux to mono and mono to flux
    public static void main(String[] args) throws IOException {

        // Use ReactiveSources.intNumbersFlux()

        // Print size of intNumbersFlux after the last item returns
//        ReactiveSources.intNumbersFlux()
//                .count()
//                .subscribe(System.out::println);

        // Collect all items of intNumbersFlux into a single list and print it
        /// this is without blocking and getting Mono as result
       /* ReactiveSources.intNumbersFlux()
                .collectList()
                .subscribe(System.out::println);
        Mono<List<Integer>> listMono = ReactiveSources.intNumbersFlux()
                .collectList();*/

        // Transform to a sequence of sums of adjacent two numbers
        ReactiveSources.intNumbersFlux()
                //.filter(num -> num > 5)
                .buffer(2)
                .map(list -> list.get(0) + list.get(1))
                .subscribe(System.out::println);

        System.out.println("Press a key to end");
        System.in.read();
    }

}
