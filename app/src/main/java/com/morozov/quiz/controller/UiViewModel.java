package com.morozov.quiz.controller;

import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

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
