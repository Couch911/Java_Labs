package org.example;

/** Перерахування фіксованих жанрів книги. */
public enum Genre {
    FICTION("Художня література"),
    SCIENCE("Наукова література"),
    FANTASY("Фантастика"),
    DETECTIVE("Детектив"),
    POETRY("Поезія"),
    HISTORY("Історія");

    private final String displayName;

    /** Ініціалізує жанр із назвою українською мовою. */
    Genre(String displayName) {
        this.displayName = displayName;
    }

    /** Повертає назву жанру українською мовою. */
    public String getDisplayName() {
        return displayName;
    }

    /** Повертає текстове представлення жанру. */
    @Override
    public String toString() {
        return displayName;
    }
}
