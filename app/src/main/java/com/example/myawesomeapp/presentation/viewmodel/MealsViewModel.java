package com.example.myawesomeapp.presentation.viewmodel;

import android.util.Log;

import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import com.example.myawesomeapp.data.api.model.MealsResponse;
import com.example.myawesomeapp.data.repository.MealRepository;
import com.example.myawesomeapp.presentation.mealdisplay.meals.adapter.MealItemViewModel;
import com.example.myawesomeapp.presentation.mealdisplay.meals.mapper.MealToViewModelMapper;

import java.util.List;

import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.annotations.NonNull;
import io.reactivex.disposables.CompositeDisposable;
import io.reactivex.observers.DisposableSingleObserver;
import io.reactivex.schedulers.Schedulers;

public class MealsViewModel extends ViewModel {

    private MealRepository mealRepository;
    private CompositeDisposable compositeDisposable;
    private MealToViewModelMapper mealToViewModelMapper;

    public MealsViewModel(MealRepository mealRepository) {
        this.mealRepository = mealRepository;
        this.compositeDisposable = new CompositeDisposable();
        this.mealToViewModelMapper = new MealToViewModelMapper();
        getMeals();
    }

    private MutableLiveData<List<MealItemViewModel>> meals = new MutableLiveData<>();
    private MutableLiveData<Boolean> isDataLoading = new MutableLiveData<Boolean>();

    public MutableLiveData<List<MealItemViewModel>> getAllMeals() {
        return meals;
    }

    public MutableLiveData<Boolean> getIsDataLoading() {
        return isDataLoading;
    }

    public CompositeDisposable getCompositeDisposable() {
        return compositeDisposable;
    }
    public MealToViewModelMapper getMealToViewModelMapper() {
        return mealToViewModelMapper;
    }
    public MealRepository getMealRepository() {
        return mealRepository;
    }

    public void getMeals() {
        compositeDisposable = getCompositeDisposable();
        mealToViewModelMapper = getMealToViewModelMapper();
        mealRepository = getMealRepository();

        meals = getAllMeals();

        compositeDisposable.clear();
        compositeDisposable.add(mealRepository.getCategories()
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .subscribeWith(new DisposableSingleObserver<MealsResponse>() {
                @Override
                public void onSuccess(@NonNull MealsResponse mealsResponse) {
                    meals.setValue(mealToViewModelMapper.map(mealsResponse.getCategories()));
                }

                @Override
                public void onError(@NonNull Throwable e) {
                    Log.d("onError", "call onError");
                }
            }));
    }

    public void removeMealFromFavorites(String id) {
        List<MealItemViewModel> mealsTmp = meals.getValue();
        for(MealItemViewModel mealItemViewModel: mealsTmp) {
            if(mealItemViewModel.getId().equals(id))
                mealItemViewModel.setFavorite(false);
        }
        meals.setValue(mealsTmp);
    }



}
