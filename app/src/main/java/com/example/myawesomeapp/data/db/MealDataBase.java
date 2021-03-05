package com.example.myawesomeapp.data.db;

import androidx.room.Database;
import androidx.room.RoomDatabase;

import com.example.myawesomeapp.data.entity.MealEntity;

/**
 * Meal Database abstract class
 */
@Database(entities = {MealEntity.class}, version = 1)
public abstract class MealDataBase extends RoomDatabase {
    public abstract MealDao mealDao();
}
