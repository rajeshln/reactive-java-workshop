package io.javabrains.reactiveworkshop;

import org.reactivestreams.Subscription;
import reactor.core.publisher.BaseSubscriber;

import java.io.IOException;

public class Exercise5 {

    public static void main(String[] args) throws IOException {

        // Use ReactiveSources.intNumberFlux() and ReactiveSources.userMono()

        // Subscribe to a flux using the error and completion hooks
//        ReactiveSources.intNumbersFlux()
//                .subscribe(e -> System.out.println(e),
//                        err -> System.out.println(err),
//                        () -> System.out.println("process is complete "));


        // Subscribe to a flux using an implementation of BaseSubscriber
       /* ReactiveSources.intNumbersFlux().subscribe(
                integer -> System.out.println(integer)
        );*/
        ReactiveSources.intNumbersFlux().subscribe(new MySubscriber());

        System.out.println("Press a key to end");
        System.in.read();
    }


}

class MySubscriber<T> extends BaseSubscriber<T> {

    @Override
    public void hookOnSubscribe(Subscription subscription) {
        System.out.println("subscribe happens ");
        request(1); // nothing will be returned if items are requested
    }

    @Override
    public void hookOnNext(T value) {
        System.out.println("---------------");
        System.out.println(value.toString() + " subscribe received ");
        request(3); // asking one more when item is retrieved
    }


}