package org.example;

import java.util.ArrayList;
import java.util.List;
import java.util.NoSuchElementException;
import java.util.Scanner;

/** Драйвер програми для роботи з книгами (базовими, електронними та паперовими). */
public class Main {

    private static final Scanner SCANNER = new Scanner(System.in);
    private static final List<Book> BOOKS = new ArrayList<>();

    /** Допоміжний запис для спільних базових полів книги. */
    private record BaseBookData(String title, String author, int price, int year,
                                String country, int pages, Genre genre) {
    }

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
                    case "2" -> createEBook();
                    case "3" -> createPaperBook();
                    case "4" -> printAllBooks();
                    case "5" -> copyBook();
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
        System.out.println("Практична робота №7");
        System.out.println("Виконав: Бондаренко К.О., група ІН-41/1");
    }

    /** Виводить пункти головного меню. */
    private static void printMenu() {
        System.out.println("\n1. Створити базову книгу (Book)");
        System.out.println("2. Створити електронну книгу (EBook)");
        System.out.println("3. Створити паперову книгу (PaperBook)");
        System.out.println("4. Вивести всі створені книги");
        System.out.println("5. Скопіювати існуючу книгу");
        System.out.println("0. Завершити роботу");
    }

    /** Зчитує спільні базові поля для будь-якого типу книги. */
    private static BaseBookData readBaseBookData() {
        String title = readNonEmptyLine("Назва: ");
        String author = readNonEmptyLine("Автор: ");
        int price = readInt("Ціна: ");
        int year = readInt("Рік видання: ");
        String country = readNonEmptyLine("Країна видання: ");
        int pages = readInt("Сторінки: ");
        Genre genre = readGenre();
        return new BaseBookData(title, author, price, year, country, pages, genre);
    }

    /** Створює базову книгу та додає її до спільної колекції. */
    private static void createBook() {
        System.out.println("\nВведіть дані для базової книги:");
        BaseBookData base = readBaseBookData();

        try {
            Book book = new Book(base.title(), base.author(), base.price(), base.year(),
                    base.country(), base.pages(), base.genre());
            BOOKS.add(book);
            System.out.println("Книгу додано: " + book);
        } catch (IllegalArgumentException e) {
            System.out.println("Помилка: " + e.getMessage());
            System.out.println("Книгу не створено.");
        }
    }

    /** Створює електронну книгу та додає її до спільної колекції. */
    private static void createEBook() {
        System.out.println("\nВведіть дані для електронної книги:");
        BaseBookData base = readBaseBookData();
        String fileFormat = readNonEmptyLine("Формат файлу : ");
        double fileSizeMb = readDouble("Розмір файлу : ");

        try {
            Book eBook = new EBook(base.title(), base.author(), base.price(), base.year(),
                    base.country(), base.pages(), base.genre(), fileFormat, fileSizeMb);
            BOOKS.add(eBook);
            System.out.println("Електронну книгу додано: " + eBook);
        } catch (IllegalArgumentException e) {
            System.out.println("Помилка: " + e.getMessage());
            System.out.println("Електронну книгу не створено.");
        }
    }

    /** Створює паперову книгу та додає її до спільної колекції. */
    private static void createPaperBook() {
        System.out.println("\nВведіть дані для паперової книги:");
        BaseBookData base = readBaseBookData();
        String coverType = readNonEmptyLine("Тип обкладинки Тверда, М'яка тощо: ");
        int weightGrams = readInt("Вага книги г: ");

        try {
            Book paperBook = new PaperBook(base.title(), base.author(), base.price(), base.year(),
                    base.country(), base.pages(), base.genre(), coverType, weightGrams);
            BOOKS.add(paperBook);
            System.out.println("Паперову книгу додано: " + paperBook);
        } catch (IllegalArgumentException e) {
            System.out.println("Помилка: " + e.getMessage());
            System.out.println("Паперову книгу не створено.");
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
            String input = readNonEmptyLine("Жанр номер або назва: ");
            try {
                int index = Integer.parseInt(input);
                if (index >= 1 && index <= genres.length) {
                    return genres[index - 1];
                }
            } catch (NumberFormatException ignored) {
            }

            for (Genre candidate : genres) {
                if (candidate.getDisplayName().equalsIgnoreCase(input)
                        || candidate.name().equalsIgnoreCase(input)) {
                    return candidate;
                }
            }
            System.out.println("Невідомий жанр. Введіть номер від 1 до " + genres.length + " або назву.");
        }
    }

    /** Поліморфно виводить список усіх створених книг через базовий тип Book. */
    private static void printAllBooks() {
        if (BOOKS.isEmpty()) {
            System.out.println("Список книг порожній.");
            return;
        }
        System.out.println("\nСписок книг:");
        for (int i = 0; i < BOOKS.size(); i++) {
            Book book = BOOKS.get(i);
            System.out.println((i + 1) + ". " + book);
        }
    }

    /** Створює копію обраної книги з урахуванням її фактичного підкласу. */
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
        Book copy;
        if (original instanceof EBook eBook) {
            copy = new EBook(eBook);
        } else if (original instanceof PaperBook paperBook) {
            copy = new PaperBook(paperBook);
        } else {
            copy = new Book(original);
        }

        BOOKS.add(copy);
        System.out.println("Створено копію книги: " + copy);
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

    /** Зчитує дробове число з обробкою некоректного введення. */
    private static double readDouble(String prompt) {
        while (true) {
            String line = readLine(prompt).replace(',', '.');
            if (line.isEmpty()) {
                System.out.println("Значення не може бути порожнім.");
                continue;
            }
            try {
                return Double.parseDouble(line);
            } catch (NumberFormatException e) {
                System.out.println("Введіть число наприклад, 4.5.");
            }
        }
    }
}