package org.example;

import java.util.Scanner;

public class Main {

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        Book[] books = new Book[5];

        System.out.println("Програму запущено. Масив для 5 книг створено.");

        // Заповнення масиву з клавіатури
        for (int i = 0; i < books.length; i++) {
            System.out.println("\nВведіть дані для книги " + (i + 1) + ":");
            books[i] = readBook(scanner);
        }

        // Виведення всіх книг через toString()
        System.out.println("\nСписок книг:");
        for (int i = 0; i < books.length; i++) {
            System.out.println((i + 1) + ". " + books[i]);
        }

        // Демонстрація equals
        System.out.println("\nbooks[0].equals(books[1]): " + books[0].equals(books[1]));

        scanner.close();
    }

    private static Book readBook(Scanner scanner) {
        System.out.print("Назва: ");
        String title = scanner.nextLine();
        System.out.print("Автор: ");
        String author = scanner.nextLine();
        int price = readInt(scanner, "Ціна: ");
        int year = readInt(scanner, "Рік видання: ");
        System.out.print("Країна: ");
        String country = scanner.nextLine();
        int pages = readInt(scanner, "Кількість сторінок: ");
        System.out.print("Жанр: ");
        String genre = scanner.nextLine();
        return new Book(title, author, price, year, country, pages, genre);
    }

    private static int readInt(Scanner scanner, String prompt) {
        while (true) {
            System.out.print(prompt);
            String line = scanner.nextLine();
            try {
                int value = Integer.parseInt(line.trim());
                if (value >= 0) {
                    return value;
                }
                System.out.println("Число не може бути від'ємним.");
            } catch (NumberFormatException e) {
                System.out.println("Введіть ціле число.");
            }
        }
    }
}