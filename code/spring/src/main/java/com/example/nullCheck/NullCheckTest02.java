package com.example.nullCheck;

public class NullCheckTest02 {
    public static void main(String[] args) {
        String compareToString = null;
        stringCompare(compareToString);
    }

    private static boolean stringCompare(String compareToString) {
        if ("nullString".equals(compareToString)) {
            return true;
        }
        return false;
    }
}
