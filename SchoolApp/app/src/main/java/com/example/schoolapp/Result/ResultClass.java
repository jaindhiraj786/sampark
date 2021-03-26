package com.example.schoolapp.Result;

import java.io.Serializable;

public class ResultClass implements Serializable {
    String total,subject,maximummarks,marks;

    public ResultClass(String total, String subject, String maximummarks, String marks) {
        this.total = total;
        this.subject = subject;
        this.maximummarks = maximummarks;
        this.marks = marks;
    }

    public String getTotal() {
        return total;
    }

    public void setTotal(String total) {
        this.total = total;
    }

    public String getSubject() {
        return subject;
    }

    public void setSubject(String subject) {
        this.subject = subject;
    }

    public String getMaximummarks() {
        return maximummarks;
    }

    public void setMaximummarks(String maximummarks) {
        this.maximummarks = maximummarks;
    }

    public String getMarks() {
        return marks;
    }

    public void setMarks(String marks) {
        this.marks = marks;
    }
}
