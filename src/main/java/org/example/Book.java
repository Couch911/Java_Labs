package org.example;

import java.util.Objects;

/**
 * Клас, що описує книгу.
 * <p>
 * Містить інформацію про назву, автора, ціну, рік видання,
 * країну видання, кількість сторінок та жанр книги.
 */
public class Book {

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
     * Створює порожню книгу без заданих значень полів.
     */
    public Book() {
    }

    /**
     * Створює книгу із заданими значеннями всіх полів.
     *
     * @param title   назва книги
     * @param author  автор книги
     * @param price   ціна книги
     * @param year    рік видання
     * @param country країна видання
     * @param pages   кількість сторінок
     * @param genre   жанр книги
     */
    public Book(String title, String author, int price, int year,
                String country, int pages, String genre) {
        this.title = title;
        this.author = author;
        this.price = price;
        this.year = year;
        this.country = country;
        this.pages = pages;
        this.genre = genre;
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
     */
    public void setTitle(String title) {
        this.title = title;
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
     */
    public void setAuthor(String author) {
        this.author = author;
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
     */
    public void setPrice(int price) {
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
     */
    public void setYear(int year) {
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
     */
    public void setCountry(String country) {
        this.country = country;
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
     */
    public void setPages(int pages) {
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
     */
    public void setGenre(String genre) {
        this.genre = genre;
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
