package com.example.quotes2;


import java.sql.Date;

public class Quote {
    public int id, userID;
    public String quote, teacher, subject;
    public Date date;

    public Quote() {

    }


    public String getSubject() {
        return subject;
    }

    public void setSubject(String subject) {
        this.subject = subject;
    }

    public Quote(int id, String quote, String teacher, String subject, Date date, int userID) {
        this.id = id;
        this.quote = quote;
        this.teacher = teacher;
        this.date = date;
        this.subject = subject;
        this.userID = userID;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getQuote() {
        return quote;
    }

    public void setQuote(String quote) {
        this.quote = quote;
    }

    public String getTeacher() {
        return teacher;
    }

    public void setTeacher(String teacher) {
        this.teacher = teacher;
    }

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }
}