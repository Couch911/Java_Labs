package org.example;

import java.util.ArrayList;
import java.util.List;
import java.util.NoSuchElementException;
import java.util.Scanner;

/** Драйвер програми для роботи з книгами та авторами. */
public class Main {

    private static final Scanner SCANNER = new Scanner(System.in);
    private static final List<Book> BOOKS = new ArrayList<>();

    /** Запускає програму, виводить шапку та обробляє головне меню. */
    public static void main(String[] args) {
        printHeader();

        try {
            boolean working = true;
            while (working) {
                printMenu();
                String choice = readLine("Вибрано: ");

                switch (choice) {
                    case "1" -> createBook();
                    case "2" -> printAllBooks();
                    case "3" -> copyBook();
                    case "4" -> printBookCount();
                    case "0" -> working = false;
                    default -> System.out.println("Виберіть вірний пункт меню.");
                }
            }
        } catch (NoSuchElementException e) {
            System.out.println("\nВведення завершено.");
        }

        System.out.println("Роботу закінчено.");
        SCANNER.close();
    }

    /** Виводить інформаційну шапку програми. */
    private static void printHeader() {
        System.out.println("Практична робота №6");
        System.out.println("Виконав(ла): Бондаренко К.О., група ІН-41/1");
    }

    /** Виводить пункти головного меню. */
    private static void printMenu() {
        System.out.println("\n1. Створити нову книгу");
        System.out.println("2. Вивести створені книги");
        System.out.println("3. Скопіювати існуючу книгу");
        System.out.println("4. Показати кількість створених книг");
        System.out.println("0. Завершити роботу");
    }

    /** Зчитує дані з клавіатури, створює книгу та додає її до списку. */
    private static void createBook() {
        System.out.println("\nВведіть дані для книги:");
        String title = readNonEmptyLine("Назва: ");
        Author author = readAuthor();
        int price = readInt("Ціна: ");
        int year = readInt("Рік видання: ");
        String country = readNonEmptyLine("Країна видання: ");
        int pages = readInt("Сторінки: ");
        Genre genre = readGenre();

        try {
            Book book = new Book(title, author, price, year, country, pages, genre);
            BOOKS.add(book);
            System.out.println("Книгу додано: " + book);
        } catch (IllegalArgumentException e) {
            System.out.println("Помилка: " + e.getMessage());
            System.out.println("Книгу не створено.");
        }
    }

    /** Зчитує дані автора та повертає валідний об'єкт Author. */
    private static Author readAuthor() {
        while (true) {
            String fullName = readNonEmptyLine("Ім'я автора: ");
            String country = readNonEmptyLine("Країна автора: ");
            int birthYear = readInt("Рік народження автора: ");
            try {
                return new Author(fullName, country, birthYear);
            } catch (IllegalArgumentException e) {
                System.out.println("Помилка даних автора: " + e.getMessage());
            }
        }
    }

    /** Виводить список жанрів та повертає обраний елемент Genre. */
    private static Genre readGenre() {
        Genre[] genres = Genre.values();
        System.out.println("Доступні жанри:");
        for (int i = 0; i < genres.length; i++) {
            System.out.println("  " + (i + 1) + ". " + genres[i].getDisplayName());
        }

        while (true) {
            String input = readNonEmptyLine("Жанр (номер або назва): ");
            try {
                int index = Integer.parseInt(input);
                if (index >= 1 && index <= genres.length) {
                    return genres[index - 1];
                }
            } catch (NumberFormatException ignored) {
            }

            for (int i = 0; i < genres.length; i++) {
                Genre candidate = genres[i];
                if (candidate.getDisplayName().equalsIgnoreCase(input)
                        || candidate.name().equalsIgnoreCase(input)) {
                    return candidate;
                }
            }
            System.out.println("Невідомий жанр. Введіть номер від 1 до " + genres.length + " або назву.");
        }
    }

    /** Виводить список усіх створених книг. */
    private static void printAllBooks() {
        if (BOOKS.isEmpty()) {
            System.out.println("Список книг порожній.");
            return;
        }
        System.out.println("\nСписок книг:");
        for (int i = 0; i < BOOKS.size(); i++) {
            System.out.println((i + 1) + ". " + BOOKS.get(i));
        }
    }

    /** Створює копію обраної книги через конструктор копіювання. */
    private static void copyBook() {
        if (BOOKS.isEmpty()) {
            System.out.println("Список книг порожній. Спочатку створіть хоча б одну книгу.");
            return;
        }

        printAllBooks();
        int number = readInt("Введіть номер книги для копіювання: ");
        if (number < 1 || number > BOOKS.size()) {
            System.out.println("Невірний номер книги.");
            return;
        }

        Book original = BOOKS.get(number - 1);
        Book copy = new Book(original);
        BOOKS.add(copy);
        System.out.println("Створено копію книги: " + copy);
        System.out.println("Усього створено книг: " + Book.getBookCount());
    }

    /** Виводить загальну кількість створених об'єктів Book. */
    private static void printBookCount() {
        System.out.println("Загальна кількість створених об'єктів Book: " + Book.getBookCount());
    }

    /** Зчитує рядок з клавіатури без крайніх пробілів. */
    private static String readLine(String prompt) {
        System.out.print(prompt);
        return SCANNER.nextLine().trim();
    }

    /** Зчитує непорожній рядок з клавіатури. */
    private static String readNonEmptyLine(String prompt) {
        while (true) {
            String line = readLine(prompt);
            if (!line.isEmpty()) {
                return line;
            }
            System.out.println("Значення повинно бути заповненим.");
        }
    }

    /** Зчитує ціле число з обробкою некоректного введення. */
    private static int readInt(String prompt) {
        while (true) {
            String line = readLine(prompt);
            if (line.isEmpty()) {
                System.out.println("Значення не може бути порожнім.");
                continue;
            }
            try {
                return Integer.parseInt(line);
            } catch (NumberFormatException e) {
                System.out.println("Введіть ціле число.");
            }
        }
    }
}
