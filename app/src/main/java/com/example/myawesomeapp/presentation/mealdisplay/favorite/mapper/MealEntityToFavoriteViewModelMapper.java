package com.example.myawesomeapp.presentation.mealdisplay.favorite.mapper;

import com.example.myawesomeapp.data.entity.MealEntity;
import com.example.myawesomeapp.presentation.mealdisplay.favorite.adapter.FavoriteItemViewModel;

import java.util.ArrayList;
import java.util.List;

public class MealEntityToFavoriteViewModelMapper {

    private FavoriteItemViewModel map(MealEntity meal) {

        FavoriteItemViewModel favoriteItemViewModel = new FavoriteItemViewModel();

        favoriteItemViewModel.setId(meal.getId());
        favoriteItemViewModel.setTitle(meal.getTitle());
        favoriteItemViewModel.setDescription(meal.getDescription());
        favoriteItemViewModel.setThumbnail(meal.getThumbnail());

        return favoriteItemViewModel;
    }

    public List<FavoriteItemViewModel> map(List<MealEntity> mealList){
        List<FavoriteItemViewModel> favoriteItemViewModelList = new ArrayList<>();
        for(MealEntity meal : mealList) {
            favoriteItemViewModelList.add(map(meal));
        }
        return favoriteItemViewModelList;
    }
}
