package org.example;

import java.util.Objects;

/** Клас, що описує електронну книгу. */
public class EBook extends Book {

    private String fileFormat;
    private double fileSizeMb;

    /** Створює електронну книгу із заданими параметрами. */
    public EBook(String title, String author, int price, int year,
                 String country, int pages, Genre genre,
                 String fileFormat, double fileSizeMb) {
        super(title, author, price, year, country, pages, genre);
        setFileFormat(fileFormat);
        setFileSizeMb(fileSizeMb);
    }

    /** Створює копію існуючої електронної книги. */
    public EBook(EBook other) {
        super(other);
        this.fileFormat = other.fileFormat;
        this.fileSizeMb = other.fileSizeMb;
    }

    /** Повертає формат файлу електронної книги. */
    public String getFileFormat() {
        return fileFormat;
    }

    /** Встановлює формат файлу електронної книги. */
    public void setFileFormat(String fileFormat) {
        this.fileFormat = checkEmpty(fileFormat, "Формат файлу").toUpperCase();
    }

    /** Повертає розмір файлу в мегабайтах. */
    public double getFileSizeMb() {
        return fileSizeMb;
    }

    /** Встановлює розмір файлу в мегабайтах. */
    public void setFileSizeMb(double fileSizeMb) {
        if (fileSizeMb <= 0) {
            throw new IllegalArgumentException("Розмір файлу має бути більшим за 0, отримано: " + fileSizeMb);
        }
        this.fileSizeMb = fileSizeMb;
    }

    /** Повертає текстове представлення електронної книги. */
    @Override
    public String toString() {
        return "EBook{title='" + getTitle() + "', author='" + getAuthor() + "', price=" + getPrice()
                + ", year=" + getYear() + ", country='" + getCountry() + "', pages=" + getPages()
                + ", genre='" + getGenre() + "', fileFormat='" + fileFormat
                + "', fileSizeMb=" + fileSizeMb + "}";
    }

    /** Порівнює електронну книгу з іншим об'єктом за всіма полями. */
    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        if (!super.equals(o)) return false;
        EBook eBook = (EBook) o;
        return Double.compare(eBook.fileSizeMb, fileSizeMb) == 0
                && Objects.equals(fileFormat, eBook.fileFormat);
    }

    /** Обчислює хеш-код електронної книги. */
    @Override
    public int hashCode() {
        return Objects.hash(super.hashCode(), fileFormat, fileSizeMb);
    }
}