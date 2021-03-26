package com.example.schoolapp.Notice;

import java.io.Serializable;
import java.util.Date;

public class NoteBroadcast implements Serializable {
    String information;
    String date_of_broadcast;
    Date dateOfBroadcast;

    public NoteBroadcast(String information, String date_of_broadcast, Date dateOfBroadcast) {
        this.information = information;
        this.date_of_broadcast = date_of_broadcast;
        this.dateOfBroadcast = dateOfBroadcast;
    }

    public String getInformation() {
        return information;
    }

    public void setInformation(String information) {
        this.information = information;
    }

    public String getDate_of_broadcast() {
        return date_of_broadcast;
    }

    public void setDate_of_broadcast(String date_of_broadcast) {
        this.date_of_broadcast = date_of_broadcast;
    }

    public Date getDateOfBroadcast() {
        return dateOfBroadcast;
    }

    public void setDateOfBroadcast(Date dateOfBroadcast) {
        this.dateOfBroadcast = dateOfBroadcast;
    }
}