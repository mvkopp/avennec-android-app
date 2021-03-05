package com.example.myawesomeapp.data.repository.mapper;

import com.example.myawesomeapp.data.api.model.Meal;
import com.example.myawesomeapp.data.entity.MealEntity;

/**
 * Meal To Meal Entity Mapper
 */
public class MealToMealEntityMapper {

    /**
     * Map the meal details to a Meal Entity
     * @param meal - the meal object
     * @return the meal entity
     */
    public MealEntity map(Meal meal) {
        MealEntity mealEntity = new MealEntity();

        // Set the details
        mealEntity.setId(meal.getId());
        mealEntity.setTitle(meal.getCategory());
        mealEntity.setDescription(meal.getDescription());
        mealEntity.setThumbnail(meal.getThumbnail());

        return mealEntity;
    }
}
