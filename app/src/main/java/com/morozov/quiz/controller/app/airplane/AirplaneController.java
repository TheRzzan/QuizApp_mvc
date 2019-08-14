package com.morozov.quiz.controller.app.airplane;

import android.content.Context;
import android.view.View;

import com.morozov.quiz.controller.Controller;
import com.morozov.quiz.controller.app.answer.AnswerViewModel;

public class AirplaneController extends Controller<AnswerViewModel> implements View.OnClickListener {

    private Context context;

    public AirplaneController(AnswerViewModel viewModel) {
        super(viewModel);
    }

    @Override
    public void onClick(View v) {

    }
}
