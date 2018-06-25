package org.misha.java8;

import java.util.Optional;
import java.util.stream.IntStream;

public class Optionals {

    static class Modem {

        private Double price;

        Modem(Double price) {
            this.price = price;
        }

        Double getPrice() {
            return price;
        }
    }

    private boolean tryFilter(Modem modem) {
        return Optional.ofNullable(modem).map(Modem::getPrice).filter(p -> p >= 10).filter(p -> p <= 15).isPresent();
    }

    private void tryIfPresent() {
        for (int i = 0; i < 10; ++i) {
            Optional<String> opt = Optional.ofNullable(i % 2 == 0 ? null : "misha");
            opt.ifPresent(name -> System.out.println(name.length()));
        }
    }

    public static void main(String... args) {
        Optionals optionals = new Optionals();
        optionals.tryIfPresent();
        IntStream.range(0, 10)
                 .forEach((int i) -> System.out.println(optionals.tryFilter(new Modem(i % 2 == 0 ? null : 11.5))));
    }
}
