package com.example.quotes2;

public class QuoteObj {
    private int id, date;
    private String teacher, subject, quote;

    public QuoteObj(int id, String quote, String teacher, String subject, int date) {
        this.id = id;
        this.quote = quote;
        this.teacher = teacher;
        this.subject = subject;
        this.date = date;
    }

    public String getQuote() {
        return quote;
    }

    public String getSubject() {
        return subject;
    }

    public String getTeacher() {
        return teacher;
    }

    public QuoteObj(int id) {
        this.id = id;
    }

    public int getDate() {
        return date;
    }

    public int getId() {
        return id;
    }
}
