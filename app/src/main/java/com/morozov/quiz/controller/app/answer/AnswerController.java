package com.morozov.quiz.controller.app.answer;

import android.content.Context;
import android.view.View;

import com.morozov.quiz.controller.Controller;
import com.morozov.quiz.utility.ActivityNavigation;
import com.morozov.quiz.utility.DataLoader;

public class AnswerController extends Controller<AnswerViewModel> implements View.OnClickListener {

    private Context context;

    AnswerController(AnswerViewModel viewModel) {
        super(viewModel);
    }

    void setContext(Context context) {
        this.context = context;
    }

    @Override
    protected void onStart() {
        initialQuestions();
    }

    private void initialQuestions() {
        viewModel().questions()
                .setValue(DataLoader.getQuestions(
                        context.getAssets(),
                        ActivityNavigation.getInstance(context).getTopicId()
                ));
    }

    @Override
    public void onClick(View v) {

    }
}
