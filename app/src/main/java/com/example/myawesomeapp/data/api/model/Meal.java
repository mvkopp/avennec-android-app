package com.example.myawesomeapp.data.api.model;

import com.google.gson.annotations.SerializedName;

import java.util.List;

public class Meal {
    @SerializedName("idCategory")
    private String id;

    @SerializedName("strCategory")
    private String category;

    @SerializedName("strCategoryDescription")
    private String description;

    @SerializedName("strCategoryThumb")
    private String thumbnail;

    private boolean isFavorite;

    public String getId() {
        return id;
    }

    public String getCategory() {
        return category;
    }

    public String getDescription() {
        return description;
    }

    public String getThumbnail() {
        return thumbnail;
    }

    public void setFavorite(boolean favorite) {
        isFavorite = favorite;
    }

    public boolean isFavorite() {
        return isFavorite;
    }
}
