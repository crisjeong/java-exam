package com.example.io;

import java.io.IOException;
import java.io.InputStream;

public class InputStreamExam01 {
    public static void main(String[] args) {
        InputStream in = null;

        try {

            int data = in.read();

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
