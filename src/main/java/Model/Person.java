package Model;


import Util.ValidationService;

public class Person implements Comparable<Person> {
    private final String name;
    private final Integer age;
    private final String email;

    private Person(PersonBuilder builder) {
        this.name = builder.name;
        this.age = builder.age;
        this.email = builder.email;
    }

    public int getAge() {
        return this.age;
    }

    public String getEmail() {
        return this.email;
    }

    public static class PersonBuilder {
        private String name;
        private Integer age;
        private String email;

        public PersonBuilder withName(String name) {
            this.name = name;
            return this;
        }

        public PersonBuilder withAge(Integer age) {
            this.age = age;
            return this;
        }
        public PersonBuilder withEmail(String email) {
            this.email = email;
            System.out.println(email);
            return this;
        }

        public Person build() {
            ValidationService.validatePerson(name, age, email);
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
                ", age=" + age +'\'' +
                ", email=" + email +
                '}';
    }
}