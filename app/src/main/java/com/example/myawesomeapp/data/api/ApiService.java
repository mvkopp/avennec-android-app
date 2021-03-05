package com.example.myawesomeapp.data.api;

import com.example.myawesomeapp.data.api.model.MealsResponse;

import io.reactivex.Single;
import retrofit2.http.GET;

/**
 * Api Service interface
 */
public interface ApiService {

    @GET("categories.php")
    Single<MealsResponse> getCategories();
}
