package com.morozov.quiz.controller.app.airplane;

import android.content.Context;
import android.view.View;

import com.morozov.quiz.controller.Controller;
import com.morozov.quiz.utility.DataLoader;

public class AirplaneController extends Controller<AirplaneViewModel> implements View.OnClickListener {

    private Context context;

    public AirplaneController(AirplaneViewModel viewModel) {
        super(viewModel);
    }

    public void setContext(Context context) {
        this.context = context;
    }

    @Override
    protected void onStart() {
        initialSections();
    }

    private void initialSections() {
        viewModel().airplanes().setValue(DataLoader.getAirplanes(context.getAssets()));
    }

    private void openSection(Integer position) {
        viewModel().selectedAirplane().setValue(position);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            default:
                openSection((Integer) v.getTag());
                break;
        }
    }
}
