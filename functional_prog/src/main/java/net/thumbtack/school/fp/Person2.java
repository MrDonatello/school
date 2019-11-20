package main.java.net.thumbtack.school.fp;

import java.util.Optional;

public class Person2 {
    private Optional<Person2> father;
    private Optional<Person2> mother;

    private Person2(Person2 father, Person2 mother) {
        this.father = Optional.ofNullable(father);
        this.mother = Optional.ofNullable(mother);
    }

    private static Optional<Person2> getMothersMotherFather(Person2 person) {

        return person.mother.flatMap(person1 -> person1.mother.flatMap(person2 -> person2.father));
    }

    public static void main(String[] args) {
        Person2 p1 = new Person2(null, new Person2(null, new Person2(new Person2(null, null), null)));
        Person2 p2 = new Person2(null, new Person2(null, new Person2(null, null)));
        Person2 p3 = new Person2(null, null);
        Optional<Person2> pR = getMothersMotherFather(p3);
    }
}
