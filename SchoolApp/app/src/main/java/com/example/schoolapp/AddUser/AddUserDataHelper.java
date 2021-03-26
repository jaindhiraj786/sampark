package com.example.schoolapp.AddUser;

public class AddUserDataHelper {
    public AddUserDataHelper(){

 }

    String name,email,phonenumber,password,classes;

    public AddUserDataHelper(String name, String email, String phonenumber, String password, String classes) {
        this.name = name;
        this.email = email;
        this.phonenumber = phonenumber;
        this.password = password;
        this.classes = classes;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPhonenumber() {
        return phonenumber;
    }

    public void setPhonenumber(String phonenumber) {
        this.phonenumber = phonenumber;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getClasses() {
        return classes;
    }

    public void setClasses(String classes) {
        this.classes = classes;
    }
}
