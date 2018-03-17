package com.example.alokshah.samsung_movieapp;

/**
 * Created by alokshah on 3/17/18.
 */

public class RowItem {
    private int imageId;
    private String name;
    private String popularity;
    private String genre;

    public RowItem(int imageId, String name, String popularity, String genre) {
        this.imageId = imageId;
        this.name = name;
        this.popularity = popularity;
        this.genre = genre;
    }
    public int getImageId() {
        return imageId;
    }
    public void setImageId(int imageId) {
        this.imageId = imageId;
    }
    public String getPopularity() {
        return popularity;
    }
    public void setPopularity(String desc) {
        this.popularity = desc;
    }
    public String getName() {
        return name;
    }
    public void setName(String title) {
        this.name = title;
    }
    public String getgenre() {
        return genre;
    }
    public void setGenre(String genre) {
        this.name = genre;
    }
    @Override
    public String toString() {
        return name + "\n" + popularity + "\n" + genre;
    }
}