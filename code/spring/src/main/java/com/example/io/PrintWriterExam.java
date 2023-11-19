package com.example.io;

import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.OutputStreamWriter;
import java.io.PrintWriter;

public class PrintWriterExam {
    public static void main(String[] args) throws Exception {

        // FileOutputStream 은 write(int) method 를 가지고 있으며, int 의 마지막 byte 만을 저장한다.
        // OutputStreamWriter 생성자에 들어온 OutputStream 의 write() method 를 이용하여 쓴다.
        // OutputStreamWriter 은 write(int) method 를 가지고 있으며, int 의 마지막 char 를 저장한다.
        // PrintWriter 의 생성자에 들어온 OutputStreamWriter 의 write() method 를 이용하여 쓴다.
        // PrintWriter 는 println(문자열) method 를 이용하여 문자열을 출력한다.
        PrintWriter printWriter = new PrintWriter(new OutputStreamWriter(new FileOutputStream("/tmp/my.txt")));
        printWriter.println("hello");
        printWriter.println("world");
        printWriter.println("!!");
        printWriter.close();
    }
}
