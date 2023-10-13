package com.example.demoweb.model;

public class Post {
    private String text;
    private Integer likes;

    public Post(String _text) {
        this(_text, 0);
    }

    public Post(String _text, Integer _likes) {
        this.text = _text;
        this.likes = _likes;
    }

    public void setLikes(int likes) {
        this.likes = likes;
    }

    public Integer getLikes() {
        return likes;
    }

    public String getText() {
        return text;
    }
}
