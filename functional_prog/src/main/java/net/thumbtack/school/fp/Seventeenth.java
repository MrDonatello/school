package main.java.net.thumbtack.school.fp;

import java.util.Arrays;
import java.util.List;

public class Seventeenth {

    public static void main(String[] args) {
        List<Integer> numbers = Arrays.asList(1, 2, 3, 5);
        List<Integer> numbers2 = Arrays.asList(6, 7, 8, 9);

        Integer sum = numbers.stream().reduce(0, (left, right) -> left + right);
        Integer product = numbers2.stream().reduce(1, (left, right) -> left * right);
    }


}
