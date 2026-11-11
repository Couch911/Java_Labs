package org.example;

import java.time.Year;
import java.util.Objects;

/** Клас, що описує автора книги. */
public class Author {

    public static final int MIN_BIRTH_YEAR = 1;

    private String fullName;
    private String country;
    private int birthYear;

    /** Створює об'єкт автора із заданими параметрами. */
    public Author(String fullName, String country, int birthYear) {
        setFullName(fullName);
        setCountry(country);
        setBirthYear(birthYear);
    }

    /** Повертає повне ім'я автора. */
    public String getFullName() {
        return fullName;
    }

    /** Встановлює повне ім'я автора. */
    public void setFullName(String fullName) {
        this.fullName = checkEmpty(fullName, "Ім'я автора");
    }

    /** Повертає країну автора. */
    public String getCountry() {
        return country;
    }

    /** Встановлює країну автора. */
    public void setCountry(String country) {
        this.country = checkEmpty(country, "Країна автора");
    }

    /** Повертає рік народження автора. */
    public int getBirthYear() {
        return birthYear;
    }

    /** Встановлює рік народження автора. */
    public void setBirthYear(int birthYear) {
        int currentYear = Year.now().getValue();
        if (birthYear < MIN_BIRTH_YEAR || birthYear > currentYear) {
            throw new IllegalArgumentException("Рік народження автора повинен бути в межах "
                    + MIN_BIRTH_YEAR + "–" + currentYear + ", отримано: " + birthYear);
        }
        this.birthYear = birthYear;
    }

    /** Перевіряє, що рядок не null і не порожній. */
    private static String checkEmpty(String value, String fieldName) {
        if (value == null || value.isBlank()) {
            throw new IllegalArgumentException(fieldName + " не може бути порожнім значенням");
        }
        return value.trim();
    }

    /** Повертає текстове представлення автора. */
    @Override
    public String toString() {
        return fullName + " (" + country + ", " + birthYear + ")";
    }

    /** Порівнює автора з іншим об'єктом за всіма полями. */
    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Author author = (Author) o;
        return birthYear == author.birthYear
                && Objects.equals(fullName, author.fullName)
                && Objects.equals(country, author.country);
    }

    /** Обчислює хеш-код автора. */
    @Override
    public int hashCode() {
        return Objects.hash(fullName, country, birthYear);
    }
}
