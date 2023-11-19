package com.example.io;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;

public class InputStreamExam01 {
    public static void main(String[] args) throws FileNotFoundException {
        InputStream in = new FileInputStream("/tmp/hellpig01.dat");

        try {

            int buf = -1;
            while ((buf = in.read()) != -1) {
                System.out.println(buf);
            }
//            int data1 = in.read();
//            System.out.println(data1);
//            int data2 = in.read();
//            System.out.println(data2);
//            int data3 = in.read();
//            System.out.println(data3);
//            int data4 = in.read();
//            System.out.println(data4);

        } catch (IOException e) {
            System.out.println(e);
        } finally {
            try {
                in.close();
            } catch (IOException e) {
                System.out.println(e);
            }
        }
    }
}
