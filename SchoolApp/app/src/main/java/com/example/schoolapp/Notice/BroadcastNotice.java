package com.example.schoolapp.Notice;

import java.io.Serializable;

public class BroadcastNotice implements Serializable {
    public BroadcastNotice(){

    }
    String title,information;

    public BroadcastNotice(String title, String information) {
        this.title = title;
        this.information = information;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getInformation() {
        return information;
    }

    public void setInformation(String information) {
        this.information = information;
    }
}
