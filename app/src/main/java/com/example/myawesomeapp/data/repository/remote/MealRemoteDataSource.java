package com.example.myawesomeapp.data.repository.remote;

import com.example.myawesomeapp.data.api.ApiService;
import com.example.myawesomeapp.data.api.model.MealsResponse;

import io.reactivex.Single;

/**
 * Meal Remote Data Source
 */
public class MealRemoteDataSource {

    private ApiService apiService;

    /**
     * Meal Remote Data Source
     * @param apiService - the api service
     */
    public MealRemoteDataSource(ApiService apiService){
        this.apiService = apiService;
    }

    /**
     * Recover all the categories
     * @return the categories
     */
    public Single<MealsResponse> getCategories() {
        return apiService.getCategories();
    }
}
