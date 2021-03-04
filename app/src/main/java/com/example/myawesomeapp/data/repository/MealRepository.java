package com.example.myawesomeapp.data.repository;

import com.example.myawesomeapp.data.api.model.Meal;
import com.example.myawesomeapp.data.api.model.MealsResponse;
import com.example.myawesomeapp.data.entity.MealEntity;
import com.example.myawesomeapp.data.repository.local.MealLocalDataSource;
import com.example.myawesomeapp.data.repository.mapper.MealToMealEntityMapper;
import com.example.myawesomeapp.data.repository.remote.MealRemoteDataSource;

import java.util.List;

import io.reactivex.Completable;
import io.reactivex.Flowable;
import io.reactivex.Single;
import io.reactivex.annotations.NonNull;
import io.reactivex.functions.BiFunction;

public class MealRepository {

    private MealRemoteDataSource mealRemoteDataSource;
    private MealLocalDataSource mealLocalDataSource;
    private MealToMealEntityMapper mealToAMealEntityMapper;

    public MealRepository(MealRemoteDataSource mealRemoteDataSource, MealLocalDataSource mealLocalDataSource, MealToMealEntityMapper mealToAMealEntityMapper){
        this.mealRemoteDataSource = mealRemoteDataSource;
        this.mealLocalDataSource = mealLocalDataSource;
        this.mealToAMealEntityMapper = mealToAMealEntityMapper;
    }

    public Single<MealsResponse> getCategories() {
        return mealRemoteDataSource.getCategories()
            .zipWith(mealLocalDataSource.getFavoriteList(), new BiFunction<MealsResponse, List<String>, MealsResponse>() {
                @NonNull
                @Override
                public MealsResponse apply(@NonNull MealsResponse mealsResponse, @NonNull List<String> strings) throws Exception {
                    for (Meal meal : mealsResponse.getCategories()) {
                        if (strings.contains(meal.getId()))
                            meal.setFavorite(true);
                    }
                    return mealsResponse;
                }
            });
    }

    public Flowable<List<MealEntity>> loadFavorites() {
        return mealLocalDataSource.loadFavorites();
    }

    public Completable addArticleToFavorites(MealEntity mealEntity) {
        return mealLocalDataSource.addMealToFavorites(mealEntity);
    }

    public Completable deleteMealFromFavorites(String id) {
        return mealLocalDataSource.deleteMealFromFavorites(id);
    }

}
