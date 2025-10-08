package Util;

public class ValidationService {
    public static void validatePerson(String name, Integer age) {
        if (name == null || name.trim().isEmpty()) {
            throw new IllegalArgumentException("Имя обязательно для заполнения");
        }
        if (age == null || age < 0) {
            throw new IllegalArgumentException("Возраст должен быть неотрицательным числом");
        }
    }
}
