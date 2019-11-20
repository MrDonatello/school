package main.java.net.thumbtack.school.fp;

import java.util.function.IntUnaryOperator;
import java.util.stream.IntStream;

public class ThirteenthAndFourteenth {

    static IntStream transform(IntStream stream, IntUnaryOperator op) {

        stream.map(op).forEach(System.out::println);
        return stream;
    }

    static IntStream transform2(IntStream stream, IntUnaryOperator op) {

        stream.map(op).parallel().forEach(System.out::println);
        return stream;
    }

    public static void main(String[] args) {

        IntStream intStream = IntStream.of(1, 2, 3);
        IntUnaryOperator op = x -> x * 10;
        transform(intStream, op);

        IntStream intStream2 = IntStream.of(1, 2, 3);
        transform2(intStream2, op);
    }
}



