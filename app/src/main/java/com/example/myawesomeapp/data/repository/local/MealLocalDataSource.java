package com.example.myawesomeapp.data.repository.local;

import com.example.myawesomeapp.data.db.MealDataBase;
import com.example.myawesomeapp.data.entity.MealEntity;

import java.util.List;

import io.reactivex.Completable;
import io.reactivex.Flowable;
import io.reactivex.Single;

/**
 * Meal Local DataSource
 */
public class MealLocalDataSource {
    private MealDataBase mealDataBase;

    /**
     * Meal Local Data Source constructor
     * @param mealDataBase - meal local database
     */
    public MealLocalDataSource(MealDataBase mealDataBase) {
        this.mealDataBase = mealDataBase;
    }

    /**
     * Recover the favorites meals
     * @return list of favorites meal
     */
    public Flowable<List<MealEntity>> loadFavorites() {
        return mealDataBase.mealDao().loadFavorites();
    }

    /**
     * Add a meal to favorite
     * @param mealEntity - a meal entity
     * @return the meal added to favorite
     */
    public Completable addMealToFavorites(MealEntity mealEntity) {
        return mealDataBase.mealDao().addMealToFavorites(mealEntity);
    }

    /**
     * Delete a meal from favorites
     * @param id - the meal id
     * @return the meal deleted
     */
    public Completable deleteMealFromFavorites(String id) {
        return mealDataBase.mealDao().deleteMealFromFavorites(id);
    }

    /**
     * Recover the favorites meals IDs
     * @return the favorites meals IDs
     */
    public Single<List<String>> getFavoriteList() {
        return mealDataBase.mealDao().getFavoriteList();
    }
}
