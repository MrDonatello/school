package main.java.net.thumbtack.school.fp;

import java.util.*;
import java.util.function.Function;
import java.util.stream.Collectors;

public class FourteenthAndSixteenth {

    public static <T> List<String> sort(Function<List<Person>, Map<String, Long>> candidates, List<Person> listPerson) {
        List<String> sort = new ArrayList<>();
        candidates.apply(listPerson).entrySet().stream().sorted(Map.Entry.comparingByValue()).forEach(e -> sort.add(e.getKey()));
        return sort;
    }

    public static void main(String[] args) {

        List<Person> listPerson = new ArrayList<Person>();

        Person person1 = new Person("Vova", 20);
        Person person2 = new Person("Misha", 40);
        Person person3 = new Person("Misha", 40);
        Person person4 = new Person("YuraLong", 40);
        Person person5 = new Person("Vova", 50);
        Person person6 = new Person("Vova", 50);
        Person person7 = new Person("Vova", 50);

        listPerson.add(person1);
        listPerson.add(person2);
        listPerson.add(person3);
        listPerson.add(person4);
        listPerson.add(person5);
        listPerson.add(person6);
        listPerson.add(person7);

        List<String> list1 = listPerson.stream().filter(p -> p.age > 30).distinct().sorted().map(Person::getName).collect(Collectors.toList());
        System.out.println(list1);

        Function<List<Person>, Map<String, Long>> mapFunction = list -> list.stream().filter(p -> p.age > 30).collect(Collectors.groupingBy(Person::getName, Collectors.counting()));
        List list = sort(mapFunction, listPerson);
        System.out.println(list);
    }

    static class Person implements Comparable<Person> {

        String name;
        int age;

        public Person(String name, int age) {
            this.name = name;
            this.age = age;
        }

        public String getName() {
            return name;
        }

        public int getAge() {
            return age;
        }

        @Override
        public boolean equals(Object o) {
            if (this == o) return true;
            if (o == null || getClass() != o.getClass()) return false;
            Person person = (Person) o;
            return age == person.age &&
                    Objects.equals(name, person.name);
        }

        @Override
        public int hashCode() {

            return Objects.hash(name, age);
        }

        @Override
        public int compareTo(Person p) {
            return (this.name.length() - p.name.length());
        }
    }
}

