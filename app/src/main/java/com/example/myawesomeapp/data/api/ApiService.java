package com.example.myawesomeapp.data.api;

import com.example.myawesomeapp.data.api.model.Meal;
import com.example.myawesomeapp.data.api.model.MealsResponse;

import io.reactivex.Single;
import retrofit2.http.GET;

public interface ApiService {

    @GET("categories.php")
    Single<MealsResponse> getCategories();
}
