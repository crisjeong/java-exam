package com.example.nullCheck;

public class NullCheckTest01 {
    public static void main(String[] args) {
        String compareToString = null;
        stringCompare(compareToString);
    }

    private static boolean stringCompare(String compareToString) {

        if (compareToString == null) {
            return false;
        }

        if (compareToString.equals("nullString")) {
            return true;
        }
        return false;
    }
}
