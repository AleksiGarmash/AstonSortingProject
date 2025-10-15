package Model;

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
            return this;
        }

        public Person build() {
            return new Person(this);
        }
    }

    @Override
    public int compareTo(Person other) {
        // Сначала сравниваем по имени
        int result = this.name.compareTo(other.name);
        if (result != 0) { // Если имена разные, возвращаем результат сравнения имен
             return result;
        } // Если имена одинаковые, сравниваем по возрасту
        result = Integer.compare(this.age, other.age);
        if (result != 0) { // Если возраста разные, возвращаем результат сравнения возрастов
            return result;
        } // Если имя и возраст совпадают, сравниваем по электронной почте
        return this.email.compareTo(other.email);
    }


    @Override
    public String toString() {
        return "\nPerson{" +
                "name='" + name + '\'' +
                ", age=" + age +'\'' +
                ", email=" + email +
                '}';
    }
}