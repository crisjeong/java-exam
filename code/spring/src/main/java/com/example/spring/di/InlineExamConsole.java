package com.example.spring.di;

public class InlineExamConsole implements ExamConsole {
    private Exam exam;

    public InlineExamConsole() {
    }

    public InlineExamConsole(Exam exam) {
        this.exam = exam;
    }

    @Override
    public void print() {
        System.out.printf("InlineExamConsole:: Total is %d, Avg is %f\n", exam.total(), exam.avg());
    }

    @Override
    public void setExam(Exam exam) {
        this.exam = exam;
    }
}
