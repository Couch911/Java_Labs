package org.example;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

/**
 * Автотести для перевірки валідації та винятків у класі {@link Book}.
 */
class BookTest {

    /**
     * Перевіряє кидання винятку при встановленні некоректного значення через сетери.
     */
    @Test
    void ExcInvVal() {
        Book book = new Book("Кобзар", "Тарас Шевченко", 350, 1840, "Україна", 240, "Поезія");

        assertThrows(IllegalArgumentException.class, () -> {
            book.setPrice(-10);
        });

        assertThrows(IllegalArgumentException.class, () -> {
            book.setPages(0);
        });

        assertThrows(IllegalArgumentException.class, () -> {
            book.setTitle("");
        });
    }

    /**
     * Перевіряє кидання винятку при створенні об'єкта з некоректними параметрами через конструктор.
     */
    @Test
    void ExcInvCons() {
        assertThrows(IllegalArgumentException.class, () -> {
            new Book("", "Автор", -5, 3000, null, 0, "Жанр");
        });
    }

    /**
     * Перевіряє успішне створення книги з коректними даними.
     */
    @Test
    void CreateBookValid() {
        Book book = new Book("Тигролови", "Іван Багряний", 280, 1944, "Україна", 320, "Роман");

        assertEquals("Тигролови", book.getTitle());
        assertEquals(280, book.getPrice());
        assertEquals(320, book.getPages());
    }
}