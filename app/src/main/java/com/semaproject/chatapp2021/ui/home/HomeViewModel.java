package com.semaproject.chatapp2021.ui.home;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

public class HomeViewModel extends ViewModel {

    private MutableLiveData<String> mText;

    public HomeViewModel() {
        mText = new MutableLiveData<>();
        mText.setValue("아마 채팅을 입력할 공간입니다.");
    }

    public LiveData<String> getText() {
        return mText;
    }
}