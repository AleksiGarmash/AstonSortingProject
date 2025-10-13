package Model;


import Util.Validation;

public class Person implements Comparable<Person> {
    private final String name;
    private final Integer age;
    private final String email;

    private Person(Builder builder) {
        this.name = builder.name;
        this.age = builder.age;
        this.email = builder.email;
    }

    public String getName() {
        return name;
    }

    public int getAge() {
        return this.age;
    }

    public String getEmail() {
        return this.email;
    }

    public static class Builder {
        private String name;
        private Integer age;
        private String email;

        public Builder withName(String name) {
            this.name = name;
            return this;
        }

        public Builder withAge(Integer age) {
            this.age = age;
            return this;
        }
        public Builder withEmail(String email) {
            this.email = email;
            System.out.println(email);
            return this;
        }

        public Person build() {
            Validation.validatePerson(name, age, email);
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