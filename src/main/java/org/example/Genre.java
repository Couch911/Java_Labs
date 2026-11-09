package org.example;

/**
 * Перерахування можливих жанрів книги.
 */
public enum Genre {
    /** Художня література. */
    FICTION("Художня література"),

    /** Наукова література. */
    SCIENCE("Наукова література"),

    /** Фантастика. */
    FANTASY("Фантастика"),

    /** Детектив. */
    DETECTIVE("Детектив"),

    /** Поезія. */
    POETRY("Поезія"),

    /** Історична література. */
    HISTORY("Історія");

    /** Назва жанру українською мовою для відображення. */
    private final String displayName;

    Genre(String displayName) {
        this.displayName = displayName;
    }

    /**
     * Повертає назву жанру українською мовою.
     */
    public String getDisplayName() {
        return displayName;
    }

    /**
     * Повертає текстове представлення жанру.
     *
     * @return назва жанру українською мовою
     */
    @Override
    public String toString() {
        return displayName;
    }
}