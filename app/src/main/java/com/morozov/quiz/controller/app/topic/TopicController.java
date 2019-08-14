package com.morozov.quiz.controller.app.topic;

import android.content.Context;
import android.view.View;

import com.morozov.quiz.controller.Controller;
import com.morozov.quiz.utility.ActivityNavigation;
import com.morozov.quiz.utility.DataLoader;

public class TopicController extends Controller<TopicViewModel> implements View.OnClickListener{

    private Context context;

    TopicController(TopicViewModel viewModel) {
        super(viewModel);
    }

    void setContext(Context context) {
        this.context = context;
    }

    @Override
    protected void onStart() {
        initialSections();
    }

    private void initialSections() {
        viewModel().topics()
                .setValue(DataLoader.getTopics(
                        context.getAssets(),
                        ActivityNavigation.getInstance(context).getSubsectionId()
                ));
    }

    private void openQuiz(Integer position) {
        viewModel().selectedTopic().setValue(position);
    }

    @Override
    public void onClick(View v) {
        openQuiz((Integer) v.getTag());
    }
}
