package com.example.schoolapp.Homework;

public class AddHomeworkClass {
    public AddHomeworkClass() {

    }


    String classes;
    String subject;
    String date;
    String description;

    public AddHomeworkClass( String classes, String subject, String date, String description) {
        this.classes = classes;
        this.subject = subject;
        this.date = date;
        this.description = description;
    }



    public String getClasses() {
        return classes;
    }

    public void setClasses(String classes) {
        this.classes = classes;
    }

    public String getSubject() {
        return subject;
    }

    public void setSubject(String subject) {
        this.subject = subject;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }
}