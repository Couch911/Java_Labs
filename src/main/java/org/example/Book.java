package org.example;

import java.time.Year;
import java.util.Objects;

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

    public static int getBookCount() {
        return bookCount;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = checkEmpty(title, "Назва");
    }

    public Author getAuthor() {
        return author;
    }

    public void setAuthor(Author author) {
        if (author == null) {
            throw new IllegalArgumentException("Автор не може бути порожнім (null)");
        }
        this.author = author;
    }

    public int getPrice() {
        return price;
    }

    public void setPrice(int price) {
        if (price <= 0) {
            throw new IllegalArgumentException("Ціна повинна бути більшою за 0, отримано: " + price);
        }
        this.price = price;
    }

    public int getYear() {
        return year;
    }

    public void setYear(int year) {
        int currentYear = Year.now().getValue();
        if (year < MIN_YEAR || year > currentYear) {
            throw new IllegalArgumentException("Рік видання повинен бути в межах "
                    + MIN_YEAR + "–" + currentYear + ", отримано: " + year);
        }
        this.year = year;
    }

    public String getCountry() {
        return country;
    }

    public void setCountry(String country) {
        this.country = checkEmpty(country, "Країна");
    }

    public int getPages() {
        return pages;
    }

    public void setPages(int pages) {
        if (pages <= 0) {
            throw new IllegalArgumentException("Кількість сторінок повинна бути більшою за 0, отримано: " + pages);
        }
        this.pages = pages;
    }

    public Genre getGenre() {
        return genre;
    }

    public void setGenre(Genre genre) {
        if (genre == null) {
            throw new IllegalArgumentException("Жанр не може бути порожнім");
        }
        this.genre = genre;
    }

    private static String checkEmpty(String value, String fieldName) {
        if (value == null || value.isBlank()) {
            throw new IllegalArgumentException(fieldName + " не може бути порожнім значенням");
        }
        return value.trim();
    }

    @Override
    public String toString() {
        return "Book{title='" + title + "', author='" + author + "', price=" + price
                + ", year=" + year + ", country='" + country + "', pages=" + pages
                + ", genre='" + genre + "'}";
    }

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

    @Override
    public int hashCode() {
        return Objects.hash(title, author, price, year, country, pages, genre);
    }
}
