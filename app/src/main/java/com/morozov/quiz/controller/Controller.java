package com.morozov.quiz.controller;

import androidx.lifecycle.Lifecycle;
import androidx.lifecycle.LifecycleObserver;
import androidx.lifecycle.OnLifecycleEvent;

public abstract class Controller<M extends UiViewModel> implements LifecycleObserver {

    private final M viewModel;

    protected M viewModel() {
        return viewModel;
    }

    public Controller(M viewModel) {
        this.viewModel = viewModel;
    }

    @OnLifecycleEvent(Lifecycle.Event.ON_START)
    protected void onStart() {

    }

    @OnLifecycleEvent(Lifecycle.Event.ON_STOP)
    protected void onStop() {

    }
}
