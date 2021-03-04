package com.example.myawesomeapp.data.repository.remote;

import com.example.myawesomeapp.data.api.ApiService;
import com.example.myawesomeapp.data.api.model.MealsResponse;

import io.reactivex.Single;

public class MealRemoteDataSource {

    private ApiService apiService;

    public MealRemoteDataSource(ApiService apiService){
        this.apiService = apiService;
    }

    public Single<MealsResponse> getCategories() {
        return apiService.getCategories();
    }
}
