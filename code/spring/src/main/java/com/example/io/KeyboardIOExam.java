package com.example.io;

import java.io.BufferedReader;
import java.io.InputStreamReader;

public class KeyboardIOExam {
    public static void main(String[] args) throws Exception {
        // 키보드로부터 한줄씩 입력받고, 한줄씩 화면에 출력한다.
        // 키보드 : System.in
        // 화면에 출력 : System.out
        // 키보드로 입력 받는다는 건 문자를 입력 받는 것 : char 단위 입출력
        // char 단위 입출력 클래스는 Reader, Writer
        // 한줄 읽기 : BufferedReader 라는 class 는 readLine 이라는 메소드를 가지고 있다.
        // 한줄 쓰기 : PrintStream, PrintWriter
        BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(System.in));
        String line = null;

        while ((line = bufferedReader.readLine()) != null) {
            System.out.println("읽어 들인 값 : " + line);
        }
    }
}
