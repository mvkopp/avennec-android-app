package com.example.myawesomeapp.presentation.mealdisplay.meals.mapper;

import com.example.myawesomeapp.data.api.model.Meal;
import com.example.myawesomeapp.presentation.mealdisplay.meals.adapter.MealItemViewModel;

import java.util.ArrayList;
import java.util.List;

public class MealToViewModelMapper {
    private MealItemViewModel map(Meal meal) {

        MealItemViewModel mealItemViewModel = new MealItemViewModel();

        mealItemViewModel.setId(meal.getId());
        mealItemViewModel.setTitle(meal.getCategory());
        mealItemViewModel.setDescription(meal.getDescription());
        mealItemViewModel.setThumbnail(meal.getThumbnail());
        mealItemViewModel.setFavorite(meal.isFavorite());
        return mealItemViewModel;
    }

    public List<MealItemViewModel> map(List<Meal> mealList){
        List<MealItemViewModel> mealItemViewModelList = new ArrayList<>();

        for(Meal meal : mealList) {
            mealItemViewModelList.add(map(meal));
        }

        return mealItemViewModelList;
    }
}
