package com.example.myawesomeapp.data.db;

import androidx.room.Dao;
import androidx.room.Insert;
import androidx.room.Query;

import com.example.myawesomeapp.data.entity.MealEntity;

import java.util.List;

import io.reactivex.Completable;
import io.reactivex.Flowable;
import io.reactivex.Single;

/**
 * Meal DAO
 */
@Dao
public interface MealDao {

    @Query("SELECT * from mealentity")
    Flowable<List<MealEntity>> loadFavorites();

    @Insert
    public Completable addMealToFavorites(MealEntity mealEntity);

    @Query("DELETE FROM mealentity WHERE id = :id")
    public Completable deleteMealFromFavorites(String id);

    @Query("SELECT id from mealentity")
    Single<List<String>> getFavoriteList();
}
