package com.example.newssources;

public class ModelOfNews {
    String title, description, type, url;

    public ModelOfNews(String title, String description, String type, String url) {
        this.title = title;
        this.description = description;
        this.type = type;
        this.url = url;
    }

    public String getTitle() {
        return title;
    }

    public String getDescription() {
        return description;
    }

    public String getType() {
        return type;
    }

    public String getUrl() {
        return url;
    }
}
