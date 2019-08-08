package com.morozov.quiz.controller.app.answer;

import android.arch.lifecycle.Observer;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.ActionBar;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.view.MenuItem;
import android.view.View;

import com.morozov.quiz.R;
import com.morozov.quiz.controller.ControllerActivity;
import com.morozov.quiz.controller.models.QuestionModel;
import com.morozov.quiz.utility.ActivityTitles;
import com.morozov.quiz.utility.ActivityUtility;
import com.morozov.quiz.utility.AppConstants;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

public class AnswerActivity extends ControllerActivity<AnswerViewModel, AnswerController> {

    @BindView(R.id.toolbar)
    Toolbar toolbar;

    @BindView(R.id.rvAnswers)
    RecyclerView rvAnswers;

    private AnswerAdapter adapter;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_answers);
        ButterKnife.bind(this);

        setSupportActionBar(toolbar);
        ActionBar actionBar = getSupportActionBar();
        if (actionBar != null) {
            actionBar.setTitle(ActivityTitles.getInstance(getApplicationContext()).getTopicName());
            actionBar.setDisplayHomeAsUpEnabled(true);
        }

        getViewModel().topicId().setValue(getIntent().getStringExtra(AppConstants.JSON_KEY_TOPIC_ID));
        getViewModel().subsectionId().setValue(getIntent().getStringExtra(AppConstants.JSON_KEY_SUBSECTION_ID));
        getViewModel().sectionId().setValue(getIntent().getStringExtra(AppConstants.JSON_KEY_SECTION_ID));

        adapter = new AnswerAdapter(getApplicationContext());
        rvAnswers.setAdapter(adapter);
        rvAnswers.setLayoutManager(new LinearLayoutManager(getApplicationContext()));
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        if (item.getItemId() == android.R.id.home)
            this.onBackPressed();

        return super.onOptionsItemSelected(item);
    }

    @Override
    protected void observe(AnswerViewModel viewModel) {
        super.observe(viewModel);

        viewModel.questions().observe(this, new Observer<List<QuestionModel>>() {
            @Override
            public void onChanged(@Nullable List<QuestionModel> questionModels) {
                adapter.setData(questionModels);
                rvAnswers.setVisibility(View.VISIBLE);
            }
        });
    }

    @Override
    protected void observeClicks(AnswerController controller) {
        super.observeClicks(controller);


    }

    @Override
    protected AnswerController createController(AnswerViewModel viewModel) {
        AnswerController answerController = new AnswerController(viewModel);
        answerController.setContext(getApplicationContext());
        return answerController;
    }

    @Override
    protected Class<AnswerViewModel> viewModel() {
        return AnswerViewModel.class;
    }

    @Override
    public void onBackPressed() {
        ActivityUtility.invokeTopicActivity(AnswerActivity.this, true,
                getViewModel().subsectionId().getValue(),
                getViewModel().sectionId().getValue(),
                false);
    }
}
