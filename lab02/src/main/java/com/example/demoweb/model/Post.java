package com.example.demoweb.model;

import java.text.SimpleDateFormat;
import java.util.Date;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import java.io.Serializable;

@Entity
public class Post implements Serializable {

    @Id
    @GeneratedValue
    private Long id;
    private String text;
    private Integer likes;
    private Date creationDate;

    public Post(Long _id, String _text, Date _createDate) {
        this(_id, _text, _createDate, 0);
    }

    public Post(Long _id, String _text, Date _createDate, Integer _likes) {
        this.id = _id;
        this.text = _text;
        this.creationDate = _createDate;
        this.likes = _likes;
    }

    public Post() {
    }

    public Long getId() { return id; }

    public void setLikes(int likes) {
        this.likes = likes;
    }

    public Integer getLikes() {
        return likes;
    }

    public String getText() {
        return text;
    }

    public String getCreationDate() {
        SimpleDateFormat formatter = new SimpleDateFormat("dd.MM.yyyy");
        return formatter.format(creationDate);
    }
}
