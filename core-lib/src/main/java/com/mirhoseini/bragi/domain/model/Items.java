package com.mirhoseini.bragi.domain.model;

/**
 * Created by Mohsen on 18/11/2016.
 */

public enum Items {
    Item1(0, "Item 1"), Item2(1, "Item 2"), Item3(2, "Item 3"), Item4(3, "Item 4"), Item5(4, "Item 5"), Item6(5, "Item 6");

    private final int value;
    private final String name;

    Items(int value, String name) {
        this.value = value;
        this.name = name;
    }

    public static Items fromValue(int value) {
        for (Items item : Items.values()) {
            if (item.value == value)
                return item;
        }
        return null;
    }

    public int getValue() {
        return value;
    }

    @Override
    public String toString() {
        return name;
    }
}
