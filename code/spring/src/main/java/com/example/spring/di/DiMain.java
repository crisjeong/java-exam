package com.example.spring.di;

import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

public class DiMain {
    public static void main(String[] args) {
//        Exam exam = new NewlecExam();
//        ExamConsole console = new GridExamConsole();
//        console.setExam(exam);

        ApplicationContext context =
                new ClassPathXmlApplicationContext("settings.xml");

        ExamConsole console = context.getBean(GridExamConsole.class);
        console.print();
    }
}
