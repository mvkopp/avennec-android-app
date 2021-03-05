package com.example.myawesomeapp.presentation.mealdisplay.favorite.adapter;

/**
 * Favorite Action Interface
 */
public interface FavoriteActionInterface {
    void onMeal(String mealTitle, String mealDescription, String mealThumbnail);
    void onRemoveFavorite(String id);
}
