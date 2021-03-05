package com.example.myawesomeapp.data.di;

import android.content.Context;

import androidx.room.Room;

import com.example.myawesomeapp.data.api.ApiService;
import com.example.myawesomeapp.data.db.MealDataBase;
import com.example.myawesomeapp.data.repository.MealRepository;
import com.example.myawesomeapp.data.repository.local.MealLocalDataSource;
import com.example.myawesomeapp.data.repository.mapper.MealToMealEntityMapper;
import com.example.myawesomeapp.data.repository.remote.MealRemoteDataSource;
import com.example.myawesomeapp.presentation.viewmodel.ViewModelFactory;
import com.facebook.stetho.okhttp3.StethoInterceptor;
import com.google.gson.Gson;

import okhttp3.OkHttpClient;
import okhttp3.logging.HttpLoggingInterceptor;
import retrofit2.Retrofit;
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory;
import retrofit2.converter.gson.GsonConverterFactory;

/**
 * Fake Depedency Injection
 */
public class FakeDepedencyInjection {
    // database
    private static MealDataBase mealDataBase;
    // repository
    public static MealRepository mealRepository;
    // api service
    public static ApiService apiService;

    public static Retrofit retrofit;
    private static Gson gson;

    private static ViewModelFactory viewModelFactory;
    private static Context applicationContext;

    /**
     * Create or Get the view model factory singleton
     * @return view model factory singleton
     */
    public static ViewModelFactory getViewModelFactory() {
        if (viewModelFactory == null)
            viewModelFactory = new ViewModelFactory(getMealRepository());
        return viewModelFactory;
    }

    /**
     * Create or Get the view meal repository singleton
     * @return meal repository singleton
     */
    public static MealRepository getMealRepository() {
        if (mealRepository == null)
            mealRepository = new MealRepository(new MealRemoteDataSource(getApiService()), new MealLocalDataSource(getMealDataBase()), new MealToMealEntityMapper());
        return mealRepository;
    }

    /**
     * Create or Get the api service singleton
     * @return api service singleton
     */
    public static ApiService getApiService() {
        if (apiService == null)
            apiService = getRetrofit().create(ApiService.class);
        return apiService;
    }

    /**
     * Create or Get the retrofit singleton
     * @return retrofit singleton
     */
    public static Retrofit getRetrofit() {
        if (retrofit == null) {
            HttpLoggingInterceptor interceptor = new HttpLoggingInterceptor();
            interceptor.setLevel(HttpLoggingInterceptor.Level.BODY);
            OkHttpClient client = new OkHttpClient.Builder()
                .addInterceptor(interceptor)
                .addNetworkInterceptor(new StethoInterceptor())
                .build();

            retrofit = new Retrofit.Builder()
                .baseUrl("https://www.themealdb.com/api/json/v1/1/")
                .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
                .client(client)
                .addConverterFactory(GsonConverterFactory.create(getGson()))
                .build();
        }
        return retrofit;
    }

    /**
     * Create or Get the Gson singleton
     * @return gson singleton
     */
    public static Gson getGson() {
        if (gson == null) {
            gson = new Gson();
        }
        return gson;
    }

    /**
     * Create or Get the meal database singleton
     * @return meal database singleton
     */
    public static MealDataBase getMealDataBase() {
        if (mealDataBase == null)
            mealDataBase = Room.databaseBuilder(applicationContext,
                MealDataBase.class, "meal-database").build();
        return mealDataBase;
    }

    /**
     * Set the app context
     * @param context - the context
     */
    public static void setApplicationContext(Context context) {
        applicationContext = context;
    }
}
