package com.morozov.quiz.controller.app.score;

import android.content.Context;
import android.view.View;

import com.morozov.quiz.R;
import com.morozov.quiz.controller.Controller;

public class ScoreController extends Controller<ScoreViewModel> implements View.OnClickListener {

    private Context context;

    ScoreController(ScoreViewModel viewModel) {
        super(viewModel);
    }

    void setContext(Context context) {
        this.context = context;
    }

    @Override
    protected void onStart() {
        viewModel().showScore().setValue(true);
    }

    private void startTestAgain() {
        viewModel().startTestAgain().setValue(true);
    }

    private void startMainMenu() {
        viewModel().startMainMenu().setValue(true);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
//            case R.id.btn_main_menu:
//                startMainMenu();
//                break;
            case R.id.btn_play_again:
                startTestAgain();
                break;
        }
    }
}
