package main.java.net.thumbtack.school.fp;

class Person {
    private Person father;
    private Person mother;

    public Person(Person father, Person mother) {
        this.father = father;
        this.mother = mother;
    }

    public static Person getMothersMotherFather(Person person) {

        Person p;
        try {
            p = person.mother.mother.father;
        } catch (NullPointerException e) {
            p = null;
        }
        return p;
    }

    public static void main(String[] args) {
        Person pTrue = new Person(null, new Person(null, new Person(new Person(null, null), null)));
        Person pNull = new Person(null, new Person(null, new Person(null, null)));
        Person pNPE = new Person(null, null);
        Person pR = getMothersMotherFather(pTrue);
    }
}