package com.example.myawesomeapp.presentation.mealdisplay.meals.adapter;

public interface MealActionInterface {
    void onMeal(String mealTitle, String mealDescription, String mealThumbnail);
    void onFavoriteToggle(String id, String title, String description, String thumbnail, boolean isFavorite);
}
