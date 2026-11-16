package org.example;

import java.util.Objects;

/** Клас, що описує паперову книгу. */
public class PaperBook extends Book {

    private String coverType;
    private int weightGrams;

    /** Створює паперову книгу із заданими параметрами. */
    public PaperBook(String title, String author, int price, int year,
                     String country, int pages, Genre genre,
                     String coverType, int weightGrams) {
        super(title, author, price, year, country, pages, genre);
        setCoverType(coverType);
        setWeightGrams(weightGrams);
    }

    /** Створює копію існуючої паперової книги. */
    public PaperBook(PaperBook other) {
        super(other);
        this.coverType = other.coverType;
        this.weightGrams = other.weightGrams;
    }

    /** Повертає тип обкладинки паперової книги. */
    public String getCoverType() {
        return coverType;
    }

    /** Встановлює тип обкладинки паперової книги. */
    public void setCoverType(String coverType) {
        this.coverType = checkEmpty(coverType, "Тип обкладинки");
    }

    /** Повертає вагу паперової книги в грамах. */
    public int getWeightGrams() {
        return weightGrams;
    }

    /** Встановлює вагу паперової книги в грамах. */
    public void setWeightGrams(int weightGrams) {
        if (weightGrams <= 0) {
            throw new IllegalArgumentException("Вага книги повинна бути більшою за 0, отримано: " + weightGrams);
        }
        this.weightGrams = weightGrams;
    }

    /** Повертає текстове представлення паперової книги. */
    @Override
    public String toString() {
        return "PaperBook{title='" + getTitle() + "', author='" + getAuthor() + "', price=" + getPrice()
                + ", year=" + getYear() + ", country='" + getCountry() + "', pages=" + getPages()
                + ", genre='" + getGenre() + "', coverType='" + coverType
                + "', weightGrams=" + weightGrams + "}";
    }

    /** Порівнює паперову книгу з іншим об'єктом за всіма полями. */
    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        if (!super.equals(o)) return false;
        PaperBook paperBook = (PaperBook) o;
        return weightGrams == paperBook.weightGrams
                && Objects.equals(coverType, paperBook.coverType);
    }

    /** Обчислює хеш-код паперової книги. */
    @Override
    public int hashCode() {
        return Objects.hash(super.hashCode(), coverType, weightGrams);
    }
}