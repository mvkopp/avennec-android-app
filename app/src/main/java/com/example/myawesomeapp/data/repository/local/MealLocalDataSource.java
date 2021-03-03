package com.example.myawesomeapp.data.repository.local;

import com.example.myawesomeapp.data.db.MealDataBase;
import com.example.myawesomeapp.data.entity.MealEntity;

import java.util.List;

import io.reactivex.Completable;
import io.reactivex.Flowable;
import io.reactivex.Single;

public class MealLocalDataSource {
    private MealDataBase mealDataBase;

    public MealLocalDataSource(MealDataBase mealDataBase) {
        this.mealDataBase = mealDataBase;
    }

    public Flowable<List<MealEntity>> loadFavorites() {
        return mealDataBase.mealDao().loadFavorites();
    }

    public Completable addMealToFavorites(MealEntity mealEntity) {
        return mealDataBase.mealDao().addMealToFavorites(mealEntity);
    }

    public Completable deleteMealFromFavorites(String id) {
        return mealDataBase.mealDao().deleteMealFromFavorites(id);
    }

    public Single<List<String>> getFavoriteList() {
        return mealDataBase.mealDao().getFavoriteList();
    }
}
