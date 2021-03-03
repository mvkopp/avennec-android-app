package com.example.myawesomeapp.ui.grid;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

public class GridViewModel extends ViewModel {

    private MutableLiveData<String> mText;

    public GridViewModel() {
        mText = new MutableLiveData<>();
        mText.setValue("This is grid fragment");
    }

    public LiveData<String> getText() {
        return mText;
    }
}
