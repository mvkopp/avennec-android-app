package com.example.myawesomeapp.presentation.viewmodel;

import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import com.example.myawesomeapp.data.entity.MealEntity;
import com.example.myawesomeapp.data.repository.MealRepository;
import com.example.myawesomeapp.data.repository.mapper.MealToMealEntityMapper;
import com.example.myawesomeapp.presentation.mealdisplay.favorite.adapter.FavoriteItemViewModel;
import com.example.myawesomeapp.presentation.mealdisplay.favorite.mapper.MealEntityToFavoriteViewModelMapper;

import java.util.List;

import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.annotations.NonNull;
import io.reactivex.disposables.CompositeDisposable;
import io.reactivex.observers.DisposableCompletableObserver;
import io.reactivex.schedulers.Schedulers;
import io.reactivex.subscribers.ResourceSubscriber;

public class MealFavoriteViewModel extends ViewModel {
    private MealRepository mealRepository;
    private CompositeDisposable compositeDisposable;
    private MealEntityToFavoriteViewModelMapper mealEntityToFavoriteViewModelMapper;
    private MealToMealEntityMapper mealToMealEntityMapper;

    private MutableLiveData<List<FavoriteItemViewModel>> favorites;
    private MutableLiveData<Boolean> isDataLoading = new MutableLiveData<Boolean>();

    final MutableLiveData<Event<String>> mealAddedEvent = new MutableLiveData<Event<String>>();
    final MutableLiveData<Event<String>> mealDeletedEvent = new MutableLiveData<Event<String>>();

    public MutableLiveData<Event<String>> getMealAddedEvent() {
        return mealAddedEvent;
    }
    public MutableLiveData<Event<String>> getMealDeletedEvent() {
        return mealDeletedEvent;
    }

    public MealFavoriteViewModel(MealRepository mealRepository) {
        this.mealRepository = mealRepository;
        compositeDisposable = new CompositeDisposable();
        mealToMealEntityMapper = new MealToMealEntityMapper();
        mealEntityToFavoriteViewModelMapper = new MealEntityToFavoriteViewModelMapper();
    }

    public MutableLiveData<List<FavoriteItemViewModel>> getFavorites() {
        isDataLoading.setValue(true);
        if (favorites == null) {
            favorites = new MutableLiveData<List<FavoriteItemViewModel>>();
            compositeDisposable.add(mealRepository.loadFavorites()
                .observeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribeWith(new ResourceSubscriber<List<MealEntity>>() {

                    @Override
                    public void onNext(List<MealEntity> mealEntities) {
                        isDataLoading.setValue(false);
                        favorites.setValue(mealEntityToFavoriteViewModelMapper.map(mealEntities));
                    }

                    @Override
                    public void onError(Throwable t) {
                        isDataLoading.setValue(false);
                    }

                    @Override
                    public void onComplete() {
                        isDataLoading.setValue(false);
                    }
                }));
        }
        return favorites;
    }

    public void addMealToFavorite(final MealEntity mealEntity) {
        compositeDisposable.add(mealRepository.addMealToFavorites(mealEntity)
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .subscribeWith(new DisposableCompletableObserver() {
                @Override
                public void onComplete() {
                    mealAddedEvent.setValue(new Event<String>(mealEntity.getId()));
                }

                @Override
                public void onError(@NonNull Throwable e) {

                }
            }));
    }

    public void deleteMealFromFavorite(final String id) {
        compositeDisposable.add(mealRepository.deleteMealFromFavorites(id)
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .subscribeWith(new DisposableCompletableObserver() {
                @Override
                public void onComplete() {
                    mealDeletedEvent.setValue(new Event<String>(id));
                }

                @Override
                public void onError(@NonNull Throwable e) {

                }
            }));
    }
}
