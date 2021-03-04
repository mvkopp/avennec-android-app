package com.example.myawesomeapp.data.repository.mapper;

import com.example.myawesomeapp.data.api.model.Meal;
import com.example.myawesomeapp.data.entity.MealEntity;

public class MealToMealEntityMapper {

    public MealEntity map(Meal meal) {
        MealEntity mealEntity = new MealEntity();

        mealEntity.setId(meal.getId());
        mealEntity.setTitle(meal.getCategory());
        mealEntity.setDescription(meal.getDescription());
        mealEntity.setThumbnail(meal.getThumbnail());

        return mealEntity;
    }
}
