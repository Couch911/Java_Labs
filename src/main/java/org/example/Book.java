package org.example;

import java.time.Year;
import java.util.Objects;

/**
 * Клас, що описує книгу.
 */
public class Book {
    public static final int MIN_YEAR = 1;
    
    private String title;
    
    private String author;
    
    private int price;
    
    private int year;
    
    private String country;
    
    private int pages;
    
    private String genre;


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
     */
    public String getTitle() {
        return title;
    }

    /**
     * Встановлює назву книги.
     */
    public void setTitle(String title) {
        this.title = checkEmpty(title, "Назва");
    }

    /**
     * Повертає автора книги.
     */
    public String getAuthor() {
        return author;
    }

    /**
     * Встановлює автора книги.
     */
    public void setAuthor(String author) {
        this.author = checkEmpty(author, "Автор");
    }

    /**
     * Повертає ціну книги.
     */
    public int getPrice() {
        return price;
    }

    /**
     * Встановлює ціну книги.
     */
    public void setPrice(int price) {
        if (price <= 0) {
            throw new IllegalArgumentException("Ціна повинна бути більшою за 0, отримано: " + price);
        }
        this.price = price;
    }

    /**
     * Повертає рік видання книги.
     */
    public int getYear() {
        return year;
    }

    /**
     * Встановлює рік видання книги.
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
     */
    public String getCountry() {
        return country;
    }

    /**
     * Встановлює країну видання книги.
     */
    public void setCountry(String country) {
        this.country = checkEmpty(country, "Країна");
    }

    /**
     * Повертає кількість сторінок книги.
     */
    public int getPages() {
        return pages;
    }

    /**
     * Встановлює кількість сторінок книги.
     */
    public void setPages(int pages) {
        if (pages <= 0) {
            throw new IllegalArgumentException("Кількість сторінок повинна бути більшою за 0, отримано: " + pages);
        }
        this.pages = pages;
    }

    /**
     * Повертає жанр книги.
     */
    public String getGenre() {
        return genre;
    }

    /**
     * Встановлює жанр книги.
     */
    public void setGenre(String genre) {
        this.genre = checkEmpty(genre, "Жанр");
    }
    
    private static String checkEmpty(String value, String fieldName) {
        if (value == null || value.isBlank()) {
            throw new IllegalArgumentException(fieldName + " не може бути порожнім значенням");
        }
        return value.trim();
    }

    /**
     * Повертає текстове представлення книги з усіма її полями.
     */
    @Override
    public String toString() {
        return "Book{title='" + title + "', author='" + author + "', price=" + price
                + ", year=" + year + ", country='" + country + "', pages=" + pages
                + ", genre='" + genre + "'}";
    }

    /**
     * Порівнює цю книгу з іншим об'єктом за всіма полями.
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
     */
    @Override
    public int hashCode() {
        return Objects.hash(title, author, price, year, country, pages, genre);
    }
}
