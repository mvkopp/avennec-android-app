package com.example.myawesomeapp.data.api.model;

import com.google.gson.annotations.SerializedName;

import java.util.List;

/**
 * Meals Response model
 */
public class MealsResponse {

    @SerializedName("categories")
    List<Meal> mealList;

    /**
     * Get all categories and its details infos
     * @return
     */
    public List<Meal> getCategories() {
        return mealList;
    }
}
