package com.morozov.quiz.controller;

import androidx.lifecycle.Lifecycle;
import androidx.lifecycle.LifecycleOwner;
import androidx.lifecycle.LifecycleRegistry;
import androidx.lifecycle.ViewModel;
import androidx.lifecycle.ViewModelProviders;
import android.os.Bundle;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

public abstract class ControllerActivity<V extends ViewModel, C extends Controller>
        extends AppCompatActivity {

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

    protected C getController() {
        return controller;
    }

    protected V getViewModel() {
        return viewModel;
    }
}
