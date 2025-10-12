package Model;


import Util.ValidationService;

public class Person implements Comparable<Person> {
    private final String name;
    private final Integer age;

    private Person(PersonBuilder builder) {
        this.name = builder.name;
        this.age = builder.age;
    }

    public int getAge() {
        return this.age;
    }

    public static class PersonBuilder {
        private String name;
        private Integer age;

        public PersonBuilder withName(String name) {
            this.name = name;
            return this;
        }

        public PersonBuilder withAge(Integer age) {
            this.age = age;
            return this;
        }

        public Person build() {
            ValidationService.validatePerson(name, age);
            return new Person(this);
        }
    }

    @Override
    public int compareTo(Person other) {
        return this.name.compareTo(other.name);
    }

    @Override
    public String toString() {
        return "Person{" +
                "name='" + name + '\'' +
                ", age=" + age +
                '}';
    }
}