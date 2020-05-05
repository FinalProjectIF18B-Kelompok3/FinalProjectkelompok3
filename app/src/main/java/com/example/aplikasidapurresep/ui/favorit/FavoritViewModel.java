package com.example.aplikasidapurresep.ui.favorit;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

public class FavoritViewModel extends ViewModel {

    private MutableLiveData<String> mText;

    public FavoritViewModel() {
        mText = new MutableLiveData<>();
        mText.setValue("This is favorit fragment");
    }

    public LiveData<String> getText() {
        return mText;
    }
}