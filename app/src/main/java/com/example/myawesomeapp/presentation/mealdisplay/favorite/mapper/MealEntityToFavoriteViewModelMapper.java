package com.example.myawesomeapp.presentation.mealdisplay.favorite.mapper;

import com.example.myawesomeapp.data.entity.MealEntity;
import com.example.myawesomeapp.presentation.mealdisplay.favorite.adapter.FavoriteItemViewModel;

import java.util.ArrayList;
import java.util.List;

/**
 * Meal Entity To Favorite View Model Mapper
 */
public class MealEntityToFavoriteViewModelMapper {

    /**
     * Map from the meal entity to the view model
     * @param meal - the meal entity
     * @return the view model
     */
    private FavoriteItemViewModel map(MealEntity meal) {

        FavoriteItemViewModel favoriteItemViewModel = new FavoriteItemViewModel();

        // set the meal details
        favoriteItemViewModel.setId(meal.getId());
        favoriteItemViewModel.setTitle(meal.getTitle());
        favoriteItemViewModel.setDescription(meal.getDescription());
        favoriteItemViewModel.setThumbnail(meal.getThumbnail());

        return favoriteItemViewModel;
    }

    /**
     * Map all the meal entity from a list to a list of view model
     * @param mealList - the list of meal entities
     * @return the view model list
     */
    public List<FavoriteItemViewModel> map(List<MealEntity> mealList){
        List<FavoriteItemViewModel> favoriteItemViewModelList = new ArrayList<>();
        for(MealEntity meal : mealList) {
            favoriteItemViewModelList.add(map(meal));
        }
        return favoriteItemViewModelList;
    }
}
