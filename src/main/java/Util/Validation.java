package Util;

public class Validation {
    public static void validatePerson(String name, Integer age, String email) {
        if (name == null || name.trim().isEmpty()) {
            throw new IllegalArgumentException("Имя обязательно для заполнения");
        }
        if (age == null || age < 0) {
            throw new IllegalArgumentException("Возраст должен быть неотрицательным числом");
        }
        if (email == null || !isValidEmail(email)) {
            throw new IllegalArgumentException("Используйте правильный формат для почты");
        }
    }
    public static Boolean isValidEmail(String email){
        if (email == null || email.isBlank()) {
            return false;
        }
        String regex = "^[A-Za-z0-9._%+-]+@[A-Za-z0-9.-]+\\.[A-Za-z]{2,}$";
        return email.matches(regex);
    }
}
