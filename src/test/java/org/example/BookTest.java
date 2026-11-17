package org.example;

import org.junit.jupiter.api.Test;

import java.time.Year;
import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class BookTest {

    @Test
    void testCreateValidBook() {
        Book book = new Book("Кобзар", "Тарас Шевченко", 350, 1840, "Україна", 720, Genre.POETRY);

        assertEquals("Кобзар", book.getTitle());
        assertEquals("Тарас Шевченко", book.getAuthor());
        assertEquals(350, book.getPrice());
        assertEquals(1840, book.getYear());
        assertEquals("Україна", book.getCountry());
        assertEquals(720, book.getPages());
        assertEquals(Genre.POETRY, book.getGenre());
    }

    @Test
    void testBookValidationThrowsException() {
        assertThrows(IllegalArgumentException.class, () ->
                new Book("", "Автор", 100, 2020, "Україна", 200, Genre.FICTION));
        assertThrows(IllegalArgumentException.class, () ->
                new Book("Назва", "   ", 100, 2020, "Україна", 200, Genre.FICTION));
        assertThrows(IllegalArgumentException.class, () ->
                new Book("Назва", "Автор", 0, 2020, "Україна", 200, Genre.FICTION));
        assertThrows(IllegalArgumentException.class, () ->
                new Book("Назва", "Автор", 100, 0, "Україна", 200, Genre.FICTION));
        assertThrows(IllegalArgumentException.class, () ->
                new Book("Назва", "Автор", 100, Year.now().getValue() + 1, "Україна", 200, Genre.FICTION));
        assertThrows(IllegalArgumentException.class, () ->
                new Book("Назва", "Автор", 100, 2020, null, 200, Genre.FICTION));
        assertThrows(IllegalArgumentException.class, () ->
                new Book("Назва", "Автор", 100, 2020, "Україна", -10, Genre.FICTION));
        assertThrows(IllegalArgumentException.class, () ->
                new Book("Назва", "Автор", 100, 2020, "Україна", 200, null));
    }

    @Test
    void testCreateValidEBookAndCopy() {
        EBook eBook = new EBook("Дюна", "Френк Герберт", 280, 1965, "США", 896, Genre.FANTASY, "epub", 5.4);

        assertEquals("EPUB", eBook.getFileFormat());
        assertEquals(5.4, eBook.getFileSizeMb(), 0.001);

        EBook copy = new EBook(eBook);
        assertEquals(eBook, copy);
        assertNotSame(eBook, copy);
    }

    @Test
    void testEBookValidationThrowsException() {
        assertThrows(IllegalArgumentException.class, () ->
                new EBook("Дюна", "Френк Герберт", 280, 1965, "США", 896, Genre.FANTASY, "", 5.4));
        assertThrows(IllegalArgumentException.class, () ->
                new EBook("Дюна", "Френк Герберт", 280, 1965, "США", 896, Genre.FANTASY, "PDF", 0));
        assertThrows(IllegalArgumentException.class, () ->
                new EBook("Дюна", "Френк Герберт", 280, 1965, "США", 896, Genre.FANTASY, "PDF", -2.5));
    }

    @Test
    void testCreateValidPaperBookAndCopy() {
        PaperBook paperBook = new PaperBook("1984", "Джордж Орвелл", 420, 1949, "Велика Британія", 328, Genre.FICTION, "Тверда", 480);

        assertEquals("Тверда", paperBook.getCoverType());
        assertEquals(480, paperBook.getWeightGrams());

        PaperBook copy = new PaperBook(paperBook);
        assertEquals(paperBook, copy);
        assertNotSame(paperBook, copy);
    }

    @Test
    void testPaperBookValidationThrowsException() {
        assertThrows(IllegalArgumentException.class, () ->
                new PaperBook("1984", "Джордж Орвелл", 420, 1949, "Велика Британія", 328, Genre.FICTION, " ", 480));
        assertThrows(IllegalArgumentException.class, () ->
                new PaperBook("1984", "Джордж Орвелл", 420, 1949, "Велика Британія", 328, Genre.FICTION, "М'яка", 0));
        assertThrows(IllegalArgumentException.class, () ->
                new PaperBook("1984", "Джордж Орвелл", 420, 1949, "Велика Британія", 328, Genre.FICTION, "М'яка", -150));
    }

    @Test
    void testPolymorphismInListAndToString() {
        List<Book> library = new ArrayList<>();
        library.add(new Book("Захар Беркут", "Іван Франко", 200, 1883, "Україна", 256, Genre.HISTORY));
        library.add(new EBook("Чистий код", "Роберт Мартін", 550, 2008, "США", 464, Genre.SCIENCE, "pdf", 12.8));
        library.add(new PaperBook("Шерлок Холмс", "Артур Конан Дойл", 490, 1892, "Велика Британія", 610, Genre.DETECTIVE, "Тверда", 750));

        assertEquals(3, library.size());
        assertTrue(library.get(0).toString().startsWith("Book{"));
        assertTrue(library.get(1).toString().startsWith("EBook{"));
        assertTrue(library.get(2).toString().startsWith("PaperBook{"));
        assertTrue(library.get(1).toString().contains("fileFormat='PDF'"));
        assertTrue(library.get(2).toString().contains("coverType='Тверда'"));
    }

    @Test
    void testEqualsAndHashCodeAcrossHierarchy() {
        Book base = new Book("Тест", "Автор", 100, 2020, "Україна", 150, Genre.FICTION);
        EBook eBook1 = new EBook("Тест", "Автор", 100, 2020, "Україна", 150, Genre.FICTION, "EPUB", 2.0);
        EBook eBook2 = new EBook("Тест", "Автор", 100, 2020, "Україна", 150, Genre.FICTION, "epub", 2.0);
        PaperBook paperBook = new PaperBook("Тест", "Автор", 100, 2020, "Україна", 150, Genre.FICTION, "Тверда", 300);

        assertEquals(eBook1, eBook2);
        assertEquals(eBook1.hashCode(), eBook2.hashCode());
        assertNotEquals(base, eBook1);
        assertNotEquals(eBook1, paperBook);
    }
}