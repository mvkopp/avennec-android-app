package com.example.myawesomeapp.data.api;

import com.example.myawesomeapp.data.api.model.Meal;

import retrofit2.Call;
import retrofit2.http.GET;

public interface ApiService {

    @GET("categories.php")
    Call<Meal> getCategories();
}
