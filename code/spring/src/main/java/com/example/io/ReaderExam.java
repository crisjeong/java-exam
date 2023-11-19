package com.example.io;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.Reader;
import java.io.FileReader;

public class ReaderExam {
    public static void main(String[] args) throws Exception {
        Reader reader = new FileReader("/tmp/hello.txt");

        int buf = -1;
        while ((buf = reader.read()) != -1) {
            System.out.println((char) buf);
        }

        reader.close();
    }
}
