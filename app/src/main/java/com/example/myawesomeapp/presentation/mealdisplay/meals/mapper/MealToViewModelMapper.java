package com.example.myawesomeapp.presentation.mealdisplay.meals.mapper;

import com.example.myawesomeapp.data.api.model.Meal;
import com.example.myawesomeapp.presentation.mealdisplay.meals.adapter.MealItemViewModel;

import java.util.ArrayList;
import java.util.List;

/**
 * Meal To View Model Mapper
 */
public class MealToViewModelMapper {

    /**
     * Map from the meal entity to the view model
     * @param meal - the meal entity
     * @return the view model
     */
    private MealItemViewModel map(Meal meal) {

        MealItemViewModel mealItemViewModel = new MealItemViewModel();

        // set the meal details
        mealItemViewModel.setId(meal.getId());
        mealItemViewModel.setTitle(meal.getCategory());
        mealItemViewModel.setDescription(meal.getDescription());
        mealItemViewModel.setThumbnail(meal.getThumbnail());
        mealItemViewModel.setFavorite(meal.isFavorite());
        return mealItemViewModel;
    }

    /**
     * Map all the meal entity from a list to a list of view model
     * @param mealList - the list of meal entities
     * @return the view model list
     */
    public List<MealItemViewModel> map(List<Meal> mealList){
        List<MealItemViewModel> mealItemViewModelList = new ArrayList<>();

        for(Meal meal : mealList) {
            mealItemViewModelList.add(map(meal));
        }

        return mealItemViewModelList;
    }
}
