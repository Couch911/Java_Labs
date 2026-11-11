package org.example;

import java.time.Year;
import java.util.Objects;

/**
 * Клас, що описує автора книги.
 * <p>
 * Використовується для демонстрації принципу агрегації у класі {@link Book}:
 * об'єкт {@code Author} може існувати незалежно від конкретної книги.
 */
public class Author {

    /** Мінімально допустимий рік народження автора. */
    public static final int MIN_BIRTH_YEAR = 1;

    /** Повне ім'я або псевдонім автора. */
    private String fullName;

    /** Країна походження автора. */
    private String country;

    /** Рік народження автора. */
    private int birthYear;

    /**
     * Створює об'єкт автора із заданими параметрами.
     *
     * @param fullName  повне ім'я автора (не порожнє)
     * @param country   країна походження автора (не порожня)
     * @param birthYear рік народження (від {@value #MIN_BIRTH_YEAR} до поточного року)
     * @throws IllegalArgumentException якщо хоча б один параметр некоректний
     */
    public Author(String fullName, String country, int birthYear) {
        setFullName(fullName);
        setCountry(country);
        setBirthYear(birthYear);
    }

    /**
     * Повертає повне ім'я автора.
     *
     * @return повне ім'я автора
     */
    public String getFullName() {
        return fullName;
    }

    /**
     * Встановлює повне ім'я автора.
     *
     * @param fullName нове повне ім'я автора
     * @throws IllegalArgumentException якщо ім'я {@code null} або порожнє
     */
    public void setFullName(String fullName) {
        this.fullName = checkEmpty(fullName, "Ім'я автора");
    }

    /**
     * Повертає країну походження автора.
     *
     * @return країна автора
     */
    public String getCountry() {
        return country;
    }

    /**
     * Встановлює країну походження автора.
     *
     * @param country нова країна походження автора
     * @throws IllegalArgumentException якщо країна {@code null} або порожня
     */
    public void setCountry(String country) {
        this.country = checkEmpty(country, "Країна автора");
    }

    /**
     * Повертає рік народження автора.
     *
     * @return рік народження автора
     */
    public int getBirthYear() {
        return birthYear;
    }

    /**
     * Встановлює рік народження автора.
     *
     * @param birthYear новий рік народження автора
     * @throws IllegalArgumentException якщо рік менший за {@value #MIN_BIRTH_YEAR}
     *                                  або більший за поточний
     */
    public void setBirthYear(int birthYear) {
        int currentYear = Year.now().getValue();
        if (birthYear < MIN_BIRTH_YEAR || birthYear > currentYear) {
            throw new IllegalArgumentException("Рік народження автора повинен бути в межах "
                    + MIN_BIRTH_YEAR + "–" + currentYear + ", отримано: " + birthYear);
        }
        this.birthYear = birthYear;
    }

    /**
     * Перевіряє, що рядок не {@code null} і не складається лише з пробілів.
     *
     * @param value     рядок для перевірки
     * @param fieldName назва поля для повідомлення про помилку
     * @return очищений рядок без крайніх пробілів
     * @throws IllegalArgumentException якщо рядок {@code null} або порожній
     */
    private static String checkEmpty(String value, String fieldName) {
        if (value == null || value.isBlank()) {
            throw new IllegalArgumentException(fieldName + " не може бути порожнім значенням");
        }
        return value.trim();
    }

    /**
     * Повертає текстове представлення автора.
     *
     * @return рядок з інформацією про автора
     */
    @Override
    public String toString() {
        return fullName + " (" + country + ", " + birthYear + ")";
    }

    /**
     * Порівнює цього автора з іншим об'єктом за всіма полями.
     *
     * @param o об'єкт для порівняння
     * @return {@code true}, якщо об'єкти рівні, інакше {@code false}
     */
    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Author author = (Author) o;
        return birthYear == author.birthYear
                && Objects.equals(fullName, author.fullName)
                && Objects.equals(country, author.country);
    }

    /**
     * Обчислює хеш-код автора на основі всіх його полів.
     *
     * @return хеш-код автора
     */
    @Override
    public int hashCode() {
        return Objects.hash(fullName, country, birthYear);
    }
}
