package org.example;

public enum Genre {
    FICTION("Художня література"),
    SCIENCE("Наукова література"),
    FANTASY("Фантастика"),
    DETECTIVE("Детектив"),
    POETRY("Поезія"),
    HISTORY("Історія");

    private final String displayName;

    Genre(String displayName) {
        this.displayName = displayName;
    }

    public String getDisplayName() {
        return displayName;
    }

    @Override
    public String toString() {
        return displayName;
    }
}
