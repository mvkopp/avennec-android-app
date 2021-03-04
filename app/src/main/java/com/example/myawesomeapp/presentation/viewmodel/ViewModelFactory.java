package com.example.myawesomeapp.presentation.viewmodel;

import androidx.annotation.NonNull;
import androidx.lifecycle.ViewModel;
import androidx.lifecycle.ViewModelProvider;

import com.example.myawesomeapp.data.repository.MealRepository;

public class ViewModelFactory implements ViewModelProvider.Factory {
    private final MealRepository mealRepository;

    public ViewModelFactory(MealRepository mealRepository) {
        this.mealRepository = mealRepository;
    }

    @NonNull
    @Override
    public <T extends ViewModel> T create(@NonNull Class<T> modelClass) {
        if (modelClass.isAssignableFrom(MealsViewModel.class))
            return (T) new MealsViewModel(mealRepository);
        if (modelClass.isAssignableFrom(MealFavoriteViewModel.class))
            return (T) new MealFavoriteViewModel(mealRepository);
        throw new IllegalArgumentException("Unknown ViewModel class");
    }
}
