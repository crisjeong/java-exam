package com.example.spring.di;

public class GridExamConsole implements ExamConsole {
    private Exam exam;

    public GridExamConsole() {
    }

    public GridExamConsole(Exam exam) {
        this.exam = exam;
    }

    @Override
    public void print() {
        System.out.printf("GridExamConsole:: Total is %d, Avg is %f\n", exam.total(), exam.avg());
    }

    @Override
    public void setExam(Exam exam) {
        this.exam = exam;
    }
}
