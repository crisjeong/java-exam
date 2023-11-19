package com.example.io;

import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStream;

public class OutputStreamExam01 {
    public static void main(String[] args) throws IOException {
        OutputStream outputStream = new FileOutputStream("/tmp/hellpig01.dat");
        outputStream.write(1);
        outputStream.write(255);
        outputStream.write(0);
        outputStream.close();
    }
}
