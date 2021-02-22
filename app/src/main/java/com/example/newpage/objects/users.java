package com.example.newpage.objects;

public class users {

    private int user_id;
    private String user_name;
    private String user_password;
    private escooters used_escooter;


    public users() {


    }

    public users(int user_id, String user_name, String user_password, escooters used_escooter) {
        this.user_id = user_id;
        this.user_name = user_name;
        this.user_password = user_password;
        this.used_escooter = used_escooter;
    }


    public int getUser_id() {
        return user_id;
    }

    public void setUser_id(int user_id) {
        this.user_id = user_id;
    }

    public String getUser_name() {
        return user_name;
    }

    public void setUser_name(String user_name) {
        this.user_name = user_name;
    }

    public String getUser_password() {
        return user_password;
    }

    public void setUser_password(String user_password) {
        this.user_password = user_password;
    }

    public escooters getUsed_escooter() {
        return used_escooter;
    }

    public void setUsed_escooter(escooters used_escooter) {
        this.used_escooter = used_escooter;
    }
}
