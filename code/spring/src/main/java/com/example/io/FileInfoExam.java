package com.example.io;

import java.io.File;
import java.io.IOException;

public class FileInfoExam {
    public static void main(String[] args) {

        if (args.length != 1) {
            System.out.println("사용법 : java FileInfo 파일 이름");
            System.exit(0);
        }

        File file = new File(args[0]);
        if (file.exists()) {
            System.out.println("file name : " + file.getName());
            System.out.println("file length : " + file.length());
            System.out.println("canRead : " + file.canRead());
            System.out.println("canWrite : " + file.canWrite());

            System.out.println("getParent : " + file.getParent());
            System.out.println("getPath : " + file.getPath());
            System.out.println("getAbsolutePath : " + file.getAbsolutePath());

            try {
                System.out.println("getCanonicalPath : " + file.getCanonicalPath());
            } catch (IOException e) {
                System.out.println(e);
            }
        } else {
            System.out.println("file not found");
        }
    }
}
