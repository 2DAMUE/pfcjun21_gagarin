package com.pfc.gagarin.entidad;

import java.util.ArrayList;

public class Mensaje {
    private String message,username,time,photo,message_id;
    private int like,dislike;
    private ArrayList<String> users_who_like_list= new ArrayList<>();
    private ArrayList<String> users_who_dislike_list= new ArrayList<>();

    public Mensaje() {
    }

    public Mensaje(String message, String username, String time, String message_id) {
        this.message = message;
        this.username = username;
        this.time = time;
        this.photo = photo;
        this.message_id = message_id;
    }

    public ArrayList<String> getUsers_who_dislike_list() {
        return users_who_dislike_list;
    }

    public void setUsers_who_dislike_list(ArrayList<String> users_who_dislike_list) {
        this.users_who_dislike_list = users_who_dislike_list;
    }

    public ArrayList<String> getUsers_who_like_list() {
        return users_who_like_list;
    }

    public void setUsers_who_like(ArrayList<String> users_who_like) {
        this.users_who_like_list = users_who_like;
    }

    public int getLike() {
        return like;
    }

    public void setLike(int like) {
        this.like = like;
    }

    public int getDislike() {
        return dislike;
    }

    public void setDislike(int dislike) {
        this.dislike = dislike;
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
