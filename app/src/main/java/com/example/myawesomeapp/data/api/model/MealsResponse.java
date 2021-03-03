package com.example.myawesomeapp.data.api.model;

import com.google.gson.annotations.SerializedName;

import java.util.List;

public class MealsResponse {

    @SerializedName("categories")
    List<Meal> mealList;

    public List<Meal> getCategories() {
        return mealList;
    }
}
