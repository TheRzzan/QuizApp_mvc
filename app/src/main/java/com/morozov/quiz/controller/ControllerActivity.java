package com.morozov.quiz.controller;

import android.arch.lifecycle.Lifecycle;
import android.arch.lifecycle.LifecycleOwner;
import android.arch.lifecycle.LifecycleRegistry;
import android.arch.lifecycle.ViewModel;
import android.arch.lifecycle.ViewModelProviders;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.view.View;

public abstract class ControllerActivity<V extends ViewModel, C extends Controller>
        extends AppCompatActivity implements LifecycleOwner {

    private LifecycleRegistry lifecycleRegistry = new LifecycleRegistry(this);

    private V viewModel;
    private C controller;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        viewModel = ViewModelProviders.of(this).get(viewModel());
        controller = createController(viewModel);
        getLifecycle().addObserver(controller);
    }

    @Override
    protected void onStart() {
        super.onStart();

        observe(viewModel);
        observeClicks(controller);
    }

    protected void observe(V viewModel) {

    }

    protected void observeClicks(C controller) {

    }

    protected abstract C createController(V viewModel);

    protected abstract Class<V> viewModel();

    protected C controller() {
        return controller;
    }

    @NonNull
    @Override
    public Lifecycle getLifecycle() {
        return lifecycleRegistry;
    }
}
