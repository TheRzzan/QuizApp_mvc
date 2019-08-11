package com.morozov.quiz.controller.app.login;

import android.arch.lifecycle.MutableLiveData;

import com.morozov.quiz.controller.UiViewModel;

public class LoginViewModel extends UiViewModel {
    private MutableLiveData<Boolean> isLogin;
    private MutableLiveData<Boolean> loadWebView;

    public LoginViewModel() {
        isLogin = new MutableLiveData<>();
        loadWebView = new MutableLiveData<>();
    }

    MutableLiveData<Boolean> isLogin() {
        return isLogin;
    }
    MutableLiveData<Boolean> loadWebView() {
        return loadWebView;
    }
}
