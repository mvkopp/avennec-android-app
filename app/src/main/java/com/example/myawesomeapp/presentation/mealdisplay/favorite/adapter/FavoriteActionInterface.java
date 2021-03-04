package com.example.myawesomeapp.presentation.mealdisplay.favorite.adapter;

public interface FavoriteActionInterface {
    void onMeal(String mealTitle, String mealDescription, String mealThumbnail);
    void onRemoveFavorite(String id);
}
