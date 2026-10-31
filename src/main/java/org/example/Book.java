package org.example;

import java.time.Year;
import java.util.Objects;

/**
 * Клас, що описує книгу.
 * <p>
 * Містить інформацію про назву, автора, ціну, рік видання,
 * країну видання, кількість сторінок та жанр книги.
 * <p>
 * Усі значення перевіряються на коректність у конструкторі та сетерах.
 * У разі некоректних даних кидається {@link IllegalArgumentException}.
 */
public class Book {

    /** Мінімально допустимий рік видання (поява книгодрукування). */
    public static final int MIN_YEAR = 1450;

    /** Назва книги. */
    private String title;

    /** Автор книги. */
    private String author;

    /** Ціна книги (у гривнях). */
    private int price;

    /** Рік видання. */
    private int year;

    /** Країна видання. */
    private String country;

    /** Кількість сторінок. */
    private int pages;

    /** Жанр книги. */
    private String genre;

    /**
     * Створює книгу із заданими значеннями всіх полів.
     * Кожне значення перевіряється відповідним сетером.
     *
     * @param title   назва книги (не порожня)
     * @param author  автор книги (не порожній)
     * @param price   ціна книги (більше 0)
     * @param year    рік видання (від {@value #MIN_YEAR} до поточного року)
     * @param country країна видання (не порожня)
     * @param pages   кількість сторінок (більше 0)
     * @param genre   жанр книги (не порожній)
     * @throws IllegalArgumentException якщо хоча б один параметр некоректний
     */
    public Book(String title, String author, int price, int year,
                String country, int pages, String genre) {
        setTitle(title);
        setAuthor(author);
        setPrice(price);
        setYear(year);
        setCountry(country);
        setPages(pages);
        setGenre(genre);
    }

    /**
     * Повертає назву книги.
     *
     * @return назва книги
     */
    public String getTitle() {
        return title;
    }

    /**
     * Встановлює назву книги.
     *
     * @param title нова назва книги
     * @throws IllegalArgumentException якщо назва {@code null} або порожня
     */
    public void setTitle(String title) {
        this.title = requireNotBlank(title, "Назва");
    }

    /**
     * Повертає автора книги.
     *
     * @return автор книги
     */
    public String getAuthor() {
        return author;
    }

    /**
     * Встановлює автора книги.
     *
     * @param author новий автор книги
     * @throws IllegalArgumentException якщо автор {@code null} або порожній
     */
    public void setAuthor(String author) {
        this.author = requireNotBlank(author, "Автор");
    }

    /**
     * Повертає ціну книги.
     *
     * @return ціна книги
     */
    public int getPrice() {
        return price;
    }

    /**
     * Встановлює ціну книги.
     *
     * @param price нова ціна книги
     * @throws IllegalArgumentException якщо ціна менша або дорівнює 0
     */
    public void setPrice(int price) {
        if (price <= 0) {
            throw new IllegalArgumentException("Ціна повинна бути більшою за 0, отримано: " + price);
        }
        this.price = price;
    }

    /**
     * Повертає рік видання книги.
     *
     * @return рік видання
     */
    public int getYear() {
        return year;
    }

    /**
     * Встановлює рік видання книги.
     *
     * @param year новий рік видання
     * @throws IllegalArgumentException якщо рік менший за {@value #MIN_YEAR}
     *                                  або більший за поточний
     */
    public void setYear(int year) {
        int currentYear = Year.now().getValue();
        if (year < MIN_YEAR || year > currentYear) {
            throw new IllegalArgumentException("Рік видання повинен бути в межах "
                    + MIN_YEAR + "–" + currentYear + ", отримано: " + year);
        }
        this.year = year;
    }

    /**
     * Повертає країну видання книги.
     *
     * @return країна видання
     */
    public String getCountry() {
        return country;
    }

    /**
     * Встановлює країну видання книги.
     *
     * @param country нова країна видання
     * @throws IllegalArgumentException якщо країна {@code null} або порожня
     */
    public void setCountry(String country) {
        this.country = requireNotBlank(country, "Країна");
    }

    /**
     * Повертає кількість сторінок книги.
     *
     * @return кількість сторінок
     */
    public int getPages() {
        return pages;
    }

    /**
     * Встановлює кількість сторінок книги.
     *
     * @param pages нова кількість сторінок
     * @throws IllegalArgumentException якщо кількість сторінок менша або дорівнює 0
     */
    public void setPages(int pages) {
        if (pages <= 0) {
            throw new IllegalArgumentException("Кількість сторінок повинна бути більшою за 0, отримано: " + pages);
        }
        this.pages = pages;
    }

    /**
     * Повертає жанр книги.
     *
     * @return жанр книги
     */
    public String getGenre() {
        return genre;
    }

    /**
     * Встановлює жанр книги.
     *
     * @param genre новий жанр книги
     * @throws IllegalArgumentException якщо жанр {@code null} або порожній
     */
    public void setGenre(String genre) {
        this.genre = requireNotBlank(genre, "Жанр");
    }

    /**
     * Перевіряє, що рядок не {@code null} і не складається лише з пробілів.
     *
     * @param value     рядок для перевірки
     * @param fieldName назва поля (для повідомлення про помилку)
     * @return рядок без пробілів на початку та в кінці
     * @throws IllegalArgumentException якщо рядок {@code null} або порожній
     */
    private static String requireNotBlank(String value, String fieldName) {
        if (value == null || value.isBlank()) {
            throw new IllegalArgumentException(fieldName + " не може бути порожнім значенням");
        }
        return value.trim();
    }

    /**
     * Повертає текстове представлення книги з усіма її полями.
     *
     * @return рядок з інформацією про книгу
     */
    @Override
    public String toString() {
        return "Book{title='" + title + "', author='" + author + "', price=" + price
                + ", year=" + year + ", country='" + country + "', pages=" + pages
                + ", genre='" + genre + "'}";
    }

    /**
     * Порівнює цю книгу з іншим об'єктом за всіма полями.
     *
     * @param o об'єкт для порівняння
     * @return {@code true}, якщо об'єкти рівні, інакше {@code false}
     */
    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Book book = (Book) o;
        return price == book.price && year == book.year && pages == book.pages
                && Objects.equals(title, book.title)
                && Objects.equals(author, book.author)
                && Objects.equals(country, book.country)
                && Objects.equals(genre, book.genre);
    }

    /**
     * Обчислює хеш-код книги на основі всіх її полів.
     *
     * @return хеш-код книги
     */
    @Override
    public int hashCode() {
        return Objects.hash(title, author, price, year, country, pages, genre);
    }
}
