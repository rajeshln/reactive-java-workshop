package io.javabrains.reactiveworkshop;

import java.io.IOException;

public class Exercise5 {

    public static void main(String[] args) throws IOException {

        // Use ReactiveSources.intNumberFlux() and ReactiveSources.userMono()

        // Subscribe to a flux using the error and completion hooks
        ReactiveSources.intNumbersFlux().subscribe(
                integer -> System.out.println(integer),
                err -> System.out.println(err.getMessage()),
                () -> System.out.println("complete")
        );

        // Subscribe to a flux using an implementation of BaseSubscriber
        ReactiveSources.intNumbersFlux().subscribe(
                integer -> System.out.println(integer)
        );

        System.out.println("Press a key to end");
        System.in.read();
    }


}