// клас Book
package org.example;
import java.util.Objects;

public class Book {

    // Оголошення приватних полів
    private String title;
    private String author;
    private int price;
    private int year;
    private String country;

    public Book() {
    }

    public Book(String title, String author, int price, int year, String country) {
        this.title = title;
        this.author = author;
        this.price = price;
        this.year = year;
        this.country = country;
    }

    // Сетери гетери
    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getAuthor() {
        return author;
    }

    public void setAuthor(String author) {
        this.author = author;
    }


    public int getPrice() {
        return price;
    }

    public void setPrice(int price) {
        this.price = price;
    }

    public int getYear() {
        return year;
    }

    public void setYear(int year) {
        this.year = year;
    }

    public String getCountry() {
        return country;
    }

    public void setCountry(String country) {
        this.country = country;
    }

    @Override
    public String toString() {
        return "Book{title='" + title + "', author='" + author + "', price=" + price
                + ", year=" + year + ", country='" + country + "'}";
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Book book = (Book) o;
        return price == book.price && year == book.year
                && Objects.equals(title, book.title)
                && Objects.equals(author, book.author)
                && Objects.equals(country, book.country);
    }

    @Override
    public int hashCode() {
        return Objects.hash(title, author, price, year, country);
    }

}