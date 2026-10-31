package org.example;

import java.util.Scanner;
import java.util.List;
import java.util.ArrayList;
import java.util.NoSuchElementException;
/**
 * Для роботи з книгами
 */

public class Main {
    /** Сканер для зчитування даних з клавіатури. */
    private static final Scanner SCANNER = new Scanner(System.in);

    private static final List<Book> BOOKS = new ArrayList<>();

    /**
     * Точка входу в програму.
     */
    public static void main(String[] args) {
        System.out.println("ПРограма запущена");

        try{
            boolean working = true;
            while (working) {
                printMenu();
                String choice = readLine("Вибрано: ");

                switch(choice) {
                    case "1" -> createBook();
                    case "2" -> printAllBooks();
                    case "0" -> working = false;
                    default -> System.out.println("Виберіть вірно");
                }
            }
        } catch (NoSuchElementException e) {
            System.out.println("\nВведення завершено");
        }

        System.out.println("Роботу закінчено");
        SCANNER.close();
    }

    /**
     * Виведення головного меню
     */


    private static void printMenu() {
        System.out.println("1. Створити нову книгу");
        System.out.println("2. Вивести створені книги");
        System.out.println("0. Завершити роботу");
    }

    /**
     * Зчитування даних для нової книги
     */

    private static void createBook() {
        System.out.println("Введіть дані для книги");
        String title = readNonEmptyLine("Назва: ");
        String author = readNonEmptyLine("Автор: ");
        int price = readInt("Ціна:");
        int year = readInt("Рік видання:");
        String country = readNonEmptyLine("Країна:");
        int pages = readInt("Сторінки:");
        String genre = readNonEmptyLine("Жанр:");

        try{
            Book book = new Book(title, author, price, year, country, pages, genre);
            BOOKS.add(book);
            System.out.println("Книгу додано: " + book);
        }catch (IllegalArgumentException e) {
            System.out.println("Помилка:" + e.getMessage());
            System.out.println("Книгу не створено");
        }
    }

    /**
     * Інформація про створені книги
     */
    private static void printAllBooks() {
        if (BOOKS.isEmpty()) {
            System.out.println("Список книг пустий");
            return;
        }
        System.out.println("Список книг:");
        for (int i = 0; i < BOOKS.size(); i++) {
            System.out.println((i + 1) + ". " + BOOKS.get(i));
        }
    }

    /**
     * Зчитування з клавіатури
     */
    private static String readLine(String prompt) {
        System.out.print(prompt);
        return SCANNER.nextLine().trim();
    }

    /**
     * Зчитування поки користувач не веде коректні дані
     */
    private static String readNonEmptyLine(String prompt) {
        while (true) {
            String line = readLine(prompt);
            if (!line.isEmpty()) {
                return line;
            }
            System.out.println("Значення повинно бути заповненим");
        }
    }
    /**
     * Зчитує ціле число
     */
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







