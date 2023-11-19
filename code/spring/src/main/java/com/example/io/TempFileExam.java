package com.example.io;

import java.io.File;
import java.io.IOException;

public class TempFileExam {
    public static void main(String[] args) {
        try {
            File file = File.createTempFile("tmp_", ".dat");
            System.out.println(file.getAbsolutePath());

            try {
                Thread.sleep(60 * 1000);
            } catch (InterruptedException e) {
                System.out.println(e);
            }

            // JVM이 종료될 때 임시파일을 자동으로 삭제한다.
            file.deleteOnExit();

        } catch (IOException e) {
            System.out.println(e);
        }
    }
}
