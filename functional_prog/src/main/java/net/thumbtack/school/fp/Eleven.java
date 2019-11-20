package main.java.net.thumbtack.school.fp;

import java.util.Arrays;
import java.util.List;

@FunctionalInterface
interface MyFunction2<T, K> {

    public K apply(T arg, T arg2);
}

public class Eleven {

    public static void main(String[] args) {

        MyFunction2<String, List<String>> splitMyF = (str, str2) -> Arrays.asList(str.split(" "));
        List<String> stringListMyF = splitMyF.apply("1", "2");
        System.out.println(stringListMyF);
    }
}


