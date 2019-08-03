package com.morozov.quiz.controller;

import android.arch.lifecycle.MutableLiveData;
import android.arch.lifecycle.ViewModel;

import com.morozov.quiz.controller.models.ErrorModel;

public abstract class UiViewModel extends ViewModel {
    private MutableLiveData<ErrorModel> error;

    public UiViewModel() {
        error = new MutableLiveData<>();
    }

    public MutableLiveData<ErrorModel> error() {
        return error;
    }
}
