package com.example.io;

import java.io.FileWriter;
import java.io.IOException;
import java.io.Writer;

public class WriterExam {
    public static void main(String[] args) throws Exception {
        Writer writer = new FileWriter("/tmp/hello.txt");
        writer.write('가');
        writer.write('나');
        writer.write('!');
        writer.close();
    }
}
