package main.java.net.thumbtack.school.fp;

import java.util.Arrays;
import java.util.List;
import java.util.function.*;
import java.util.Date;

@FunctionalInterface
interface MyFunction<T, K> {

    public K apply(T arg);
}

public class OneToTen {

    static class Person {
        private String firstName;

        Person(String firstName) {
            this.firstName = firstName;
        }
    }

    public static void main(String[] args) {

        String s = "123 456 789 10 11";
        Function<String, List<String>> split = str -> Arrays.asList(str.split(" "));
        List<String> stringList = split.apply(s);

        Function<List<?>, Integer> count = c -> c.size();
        Integer i = count.apply(stringList);
        System.out.println(i);

        Function<List<?>, Integer> ref = List::size;
        Integer refInt = ref.apply(stringList);
        System.out.println(refInt);

        Function splitAndCount = split.andThen(count);
        System.out.println(splitAndCount.apply(s));

        Function splitAndCountC = count.compose(split);
        System.out.println(splitAndCountC.apply(s));

        Function<String, Person> create = p -> new Person(p);
        Person person = create.apply("name");

        Function<String, Person> createRef = Person::new;
        Person personRef = createRef.apply("name");

        BiFunction<Integer, Integer, Integer> max = Math::max;
        int m = max.apply(2, 3);

        Supplier<Date> getCurrentDate = Date::new;
        System.out.println(getCurrentDate.get());

        Predicate<Integer> isEven = p -> p % 2 == 0;
        System.out.println(isEven.test(10));

        BiPredicate<Integer, Integer> areEqual = Integer::equals;
        System.out.println(areEqual.test(5, 5));

        MyFunction<String, List<String>> splitMyF = str -> Arrays.asList(str.split(" "));
        List<String> stringListMyF = splitMyF.apply(s);
        System.out.println(stringListMyF);
    }
}
