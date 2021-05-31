package com.pfc.gagarin.entidad;

public class Mensaje {
    private String message,username,time,photo,message_id;

    public Mensaje() {
    }

    public Mensaje(String message, String username, String time, String message_id) {
        this.message = message;
        this.username = username;
        this.time = time;
        this.photo = photo;
        this.message_id = message_id;
    }

    public String getMessage_id() {
        return message_id;
    }

    public void setMessage_id(String message_id) {
        this.message_id = message_id;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getTime() {
        return time;
    }

    public void setTime(String time) {
        this.time = time;
    }

    public String getPhoto() {
        return photo;
    }

    public void setPhoto(String photo) {
        this.photo = photo;
    }

    @Override
    public String toString() {
        return "Mensaje{" +
                "message='" + message + '\'' +
                ", username='" + username + '\'' +
                ", time='" + time + '\'' +
                ", photo='" + photo + '\'' +
                ", message_id='" + message_id + '\'' +
                '}';
    }
}
