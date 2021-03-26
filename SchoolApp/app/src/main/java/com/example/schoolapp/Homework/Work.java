package com.example.schoolapp.Homework;

import java.io.Serializable;

public class Work implements Serializable {

    public Work(){

    }


   String classes,date,description,subject;

    public Work(String classes, String date, String description,  String subject) {
        this.classes = classes;
        this.date = date;
        this.description = description;
        this.subject = subject;
    }

    public String getClasses() {
        return classes;
    }

    public void setClasses(String classes) {
        this.classes = classes;
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

    public String getSubject() {
        return subject;
    }

    public void setSubject(String subject) {
        this.subject = subject;
    }
}
