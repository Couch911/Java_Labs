package org.example;

import java.time.Year;
import java.util.Objects;

/** Клас, що описує книгу з валідацією полів та агрегацією автора. */
public class Book {
    public static final int MIN_YEAR = 1;

    private static int bookCount = 0;

    private String title;
    private Author author;
    private int price;
    private int year;
    private String country;
    private int pages;
    private Genre genre;

    /** Створює книгу із заданими параметрами та збільшує лічильник. */
    public Book(String title, Author author, int price, int year,
                String country, int pages, Genre genre) {
        setTitle(title);
        setAuthor(author);
        setPrice(price);
        setYear(year);
        setCountry(country);
        setPages(pages);
        setGenre(genre);
        bookCount++;
    }

    /** Створює копію існуючої книги та збільшує лічильник. */
    public Book(Book other) {
        if (other == null) {
            throw new IllegalArgumentException("Об'єкт для копіювання не може бути null");
        }
        this.title = other.title;
        this.author = other.author;
        this.price = other.price;
        this.year = other.year;
        this.country = other.country;
        this.pages = other.pages;
        this.genre = other.genre;
        bookCount++;
    }

    /** Повертає загальну кількість створених об'єктів Book. */
    public static int getBookCount() {
        return bookCount;
    }

    /** Повертає назву книги. */
    public String getTitle() {
        return title;
    }

    /** Встановлює назву книги. */
    public void setTitle(String title) {
        this.title = checkEmpty(title, "Назва");
    }

    /** Повертає автора книги. */
    public Author getAuthor() {
        return author;
    }

    /** Встановлює автора книги. */
    public void setAuthor(Author author) {
        if (author == null) {
            throw new IllegalArgumentException("Автор не може бути порожнім (null)");
        }
        this.author = author;
    }

    /** Повертає ціну книги. */
    public int getPrice() {
        return price;
    }

    /** Встановлює ціну книги. */
    public void setPrice(int price) {
        if (price <= 0) {
            throw new IllegalArgumentException("Ціна повинна бути більшою за 0, отримано: " + price);
        }
        this.price = price;
    }

    /** Повертає рік видання книги. */
    public int getYear() {
        return year;
    }

    /** Встановлює рік видання книги. */
    public void setYear(int year) {
        int currentYear = Year.now().getValue();
        if (year < MIN_YEAR || year > currentYear) {
            throw new IllegalArgumentException("Рік видання повинен бути в межах "
                    + MIN_YEAR + "–" + currentYear + ", отримано: " + year);
        }
        this.year = year;
    }

    /** Повертає країну видання книги. */
    public String getCountry() {
        return country;
    }

    /** Встановлює країну видання книги. */
    public void setCountry(String country) {
        this.country = checkEmpty(country, "Країна");
    }

    /** Повертає кількість сторінок книги. */
    public int getPages() {
        return pages;
    }

    /** Встановлює кількість сторінок книги. */
    public void setPages(int pages) {
        if (pages <= 0) {
            throw new IllegalArgumentException("Кількість сторінок повинна бути більшою за 0, отримано: " + pages);
        }
        this.pages = pages;
    }

    /** Повертає жанр книги. */
    public Genre getGenre() {
        return genre;
    }

    /** Встановлює жанр книги. */
    public void setGenre(Genre genre) {
        if (genre == null) {
            throw new IllegalArgumentException("Жанр не може бути порожнім");
        }
        this.genre = genre;
    }

    /** Перевіряє, що рядок не null і не порожній. */
    private static String checkEmpty(String value, String fieldName) {
        if (value == null || value.isBlank()) {
            throw new IllegalArgumentException(fieldName + " не може бути порожнім значенням");
        }
        return value.trim();
    }

    /** Повертає текстове представлення книги. */
    @Override
    public String toString() {
        return "Book{title='" + title + "', author='" + author + "', price=" + price
                + ", year=" + year + ", country='" + country + "', pages=" + pages
                + ", genre='" + genre + "'}";
    }

    /** Порівнює книгу з іншим об'єктом за всіма полями. */
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

    /** Обчислює хеш-код книги. */
    @Override
    public int hashCode() {
        return Objects.hash(title, author, price, year, country, pages, genre);
    }
}
