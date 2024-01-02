package io.javabrains.reactiveworkshop;

import java.io.IOException;

public class Exercise4 {

    public static void main(String[] args) throws IOException {

        // Use ReactiveSources.intNumberMono()

        // Print the value from intNumberMono when it emits
        ReactiveSources.intNumberMono().subscribe(e -> System.out.println(e));

        // Get the value from the Mono into an integer variable
        Integer block = ReactiveSources.intNumberMono().block(); // blocking is not good things
        /// that will lose reactive benefits
        System.out.println("block  >>>>  " + block);
        System.out.println("Press a key to end");
        System.in.read();
    }

}
