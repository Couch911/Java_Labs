package org.example;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

/** Модульні тести для перевірки класів Book та Author. */
class BookTest {

    /** Перевіряє кидання винятку при некоректних значеннях у сетерах. */
    @Test
    void ExcInvVal() {
        Author author = new Author("Тарас Шевченко", "Україна", 1814);
        Book book = new Book("Кобзар", author, 350, 1840, "Україна", 240, Genre.POETRY);

        assertThrows(IllegalArgumentException.class, () -> book.setPrice(-10));
        assertThrows(IllegalArgumentException.class, () -> book.setPages(0));
        assertThrows(IllegalArgumentException.class, () -> book.setTitle(""));
        assertThrows(IllegalArgumentException.class, () -> book.setAuthor(null));
        assertThrows(IllegalArgumentException.class, () -> book.setGenre(null));
    }

    /** Перевіряє кидання винятку при некоректних параметрах у конструкторах. */
    @Test
    void ExcInvCons() {
        Author author = new Author("Іван Франко", "Україна", 1856);
        assertThrows(IllegalArgumentException.class, () -> {
            new Book("", author, -5, 3000, null, 0, Genre.FICTION);
        });
        assertThrows(IllegalArgumentException.class, () -> {
            new Author("", "Україна", 3000);
        });
    }

    /** Перевіряє успішне створення книги з валідними даними та агрегацією. */
    @Test
    void CreateBookValid() {
        Author author = new Author("Іван Багряний", "Україна", 1906);
        Book book = new Book("Тигролови", author, 280, 1944, "Україна", 320, Genre.FICTION);

        assertEquals("Тигролови", book.getTitle());
        assertEquals(author, book.getAuthor());
        assertEquals(280, book.getPrice());
        assertEquals(320, book.getPages());
        assertEquals(Genre.FICTION, book.getGenre());
    }

    /** Перевіряє роботу конструктора копіювання та статичного лічильника. */
    @Test
    void CopyConstructorAndStaticCounter() {
        int before = Book.getBookCount();
        Author author = new Author("Леся Українка", "Україна", 1871);
        Book original = new Book("Лісова пісня", author, 220, 1911, "Україна", 160, Genre.POETRY);
        Book copy = new Book(original);

        assertEquals(original, copy);
        assertNotSame(original, copy);
        assertEquals(before + 2, Book.getBookCount());
        assertThrows(IllegalArgumentException.class, () -> new Book(null));
    }
}
