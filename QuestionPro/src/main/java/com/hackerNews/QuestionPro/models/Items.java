package com.hackerNews.QuestionPro.models;

import org.springframework.data.mongodb.core.mapping.Document;

@Document(collection = "items")
public class Items {

    private int id;
    private String title;
    private String url;
    private long score;
    private long time;
    private String by;
    private String text;


    public Items() {
    }

    public Items(int id, String title, String url, long score, long time, String by, String text) {
        this.id = id;
        this.title = title;
        this.url = url;
        this.score = score;
        this.time = time;
        this.by = by;
        this.text = text;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public long getScore() {
        return score;
    }

    public void setScore(long score) {
        this.score = score;
    }

    public long getTime() {
        return time;
    }

    public void setTime(long time) {
        this.time = time;
    }

    public String getBy() {
        return by;
    }

    public void setBy(String by) {
        this.by = by;
    }

    public String getText() {
        return text;
    }

    public void setText(String text) {
        this.text = text;
    }

}
