package com.example.registration_api.entity;

public class PageContent {
    private String title;
    private String content;

    public PageContent(String title, String content) {
        this.title = title;
        this.content = content;
    }

    // Getters and setters
    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }
}