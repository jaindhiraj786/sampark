package com.example.schoolapp.Homework;

import java.io.Serializable;

public class HomeworkMenu implements Serializable {

    String subject;
    String date;
    String description;

    public HomeworkMenu(String subject, String date, String description) {
        this.subject = subject;
        this.date = date;
        this.description = description;
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
