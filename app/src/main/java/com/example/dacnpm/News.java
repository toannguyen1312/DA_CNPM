package com.example.dacnpm;

public class News {

    private int newID;
    private String title;

    private Category category;

    private String publishDate;
    private String description;
    private String imgURL;
    private String sourceURL;

    public News(String title, Category category, String publishDate, String description, String imgURL, String sourceURL) {

        this.title = title;
        this.category = category;
        this.publishDate = publishDate;
        this.description = description;
        this.imgURL = imgURL;
        this.sourceURL = sourceURL;
    }

    public int getNewID() {
        return newID;
    }

    public void setNewID(int newID) {
        this.newID = newID;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public Category getCategory() {
        return category;
    }

    public void setCategory(Category category) {
        this.category = category;
    }

    public String getPublishDate() {
        return publishDate;
    }

    public void setPublishDate(String publishDate) {
        this.publishDate = publishDate;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getImgURL() {
        return imgURL;
    }

    public void setImgURL(String imgURL) {
        this.imgURL = imgURL;
    }

    public String getSourceURL() {
        return sourceURL;
    }

    public void setSourceURL(String sourceURL) {
        this.sourceURL = sourceURL;
    }
}