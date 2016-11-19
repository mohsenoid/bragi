package com.mirhoseini.bragi.domain.model;

/**
 * Created by Mohsen on 18/11/2016.
 */

public enum Sections {
    Section1(0, "Section 1"), Section2(1, "Section 2"), Section3(2, "Section 3"), Section4(3, "Section 4");

    private int value;
    private String name;

    Sections(int value, String name) {
        this.value = value;
        this.name = name;
    }

    public static Sections fromValue(int value) {
        for (Sections section : Sections.values()) {
            if (section.value == value)
                return section;
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