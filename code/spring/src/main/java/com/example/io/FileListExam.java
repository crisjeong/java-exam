package com.example.io;

import java.io.File;
import java.sql.SQLOutput;
import java.util.Objects;

public class FileListExam {
    public static void main(String[] args) {
        for (File file : Objects.requireNonNull(new File("E:\\workspace\\java-exam\\code\\spring\\tmp").listFiles(), "file 목록이 없습니다.")) {
            System.out.println("이름 / 타입 : " + file.getName() + " / " + (file.isDirectory() ? "폴더" : "파일"));
        }
    }
}
