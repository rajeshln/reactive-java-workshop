package io.javabrains.reactiveworkshop;

import java.io.IOException;

public class Exercise7 {


    public static void main(String[] args) throws IOException {

        // Use ReactiveSources.intNumberMono() and ReactiveSources.userMono()

        // Print all values from intNumbersFlux that's greater than 5
      /*  ReactiveSources.intNumbersFlux()
                // .log() // will log everything before filter
                .filter(n -> n > 5)
                //.log() // will log only filtered items
                .subscribe(System.out::println);*/


        // Print 10 times each value from intNumbersFlux that's greater than 5
        /*ReactiveSources.intNumbersFlux()
                .filter(n -> n > 5)
                .map(n -> n * 10)
                //.log() // the position of log changes what it is going to print
                .subscribe(System.out::println);*/

        // Print 10 times each value from intNumbersFlux for the first 3 numbers emitted that's greater than 5
       /* ReactiveSources.intNumbersFlux()
                .filter(n -> n > 5)
                .take(3) // take can be here or after map
                .map(n -> n * 10)
                .subscribe(System.out::println);*/

        // Print each value from intNumbersFlux that's greater than 20. Print -1 if no elements are found
//        ReactiveSources.intNumbersFlux()
//                //  .log()
//                .filter(n -> n > 8)
//                //    .log()
//                .defaultIfEmpty(-1)
//                .subscribe(System.out::println);

        // Switch ints from intNumbersFlux to the right user from userFlux
        // implementing flat map
      /*  ReactiveSources.intNumbersFlux()
                .flatMap(id -> ReactiveSources.userFlux().filter(user -> user.getId() == id))
                .subscribe(System.out::println);
*/

        // Print only distinct numbers from intNumbersFluxWithRepeat() -flux which has some repeated numbers
//        ReactiveSources.intNumbersFluxWithRepeat()
//                //.log()
//                .distinct()
//                .subscribe(System.out::println);

        // Print from intNumbersFluxWithRepeat excluding immediately repeating numbers
        ReactiveSources.intNumbersFluxWithRepeat()
                //.log()
                .distinctUntilChanged()
                .log()
                .subscribe();

        System.out.println("Press a key to end");
        System.in.read();
    }

}
