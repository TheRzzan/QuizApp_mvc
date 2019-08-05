package com.morozov.quiz.controller.app.quiz;

import android.annotation.SuppressLint;
import android.arch.lifecycle.Observer;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.morozov.quiz.R;
import com.morozov.quiz.controller.ControllerActivity;
import com.morozov.quiz.controller.models.QuestionModel;
import com.morozov.quiz.controller.models.ScoreModel;
import com.morozov.quiz.utility.ActivityUtility;
import com.morozov.quiz.utility.AppConstants;

import butterknife.BindView;
import butterknife.ButterKnife;

public class QuizActivity extends ControllerActivity<QuizViewModel, QuizController> {

    @BindView(R.id.toolbar)
    Toolbar toolbar;

    @BindView(R.id.rvAnswers)
    RecyclerView rvAnswers;

    @BindView(R.id.tvQuestionTitle)
    TextView questionTitle;

    @BindView(R.id.tvQuestionText)
    TextView questionText;

    @BindView(R.id.btn_next)
    Button btnNext;

    private QuizAdapter adapter;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_quiz);
        ButterKnife.bind(this);

        setSupportActionBar(toolbar);

        getViewModel().topicId().setValue(getIntent().getStringExtra(AppConstants.JSON_KEY_TOPIC_ID));
        getViewModel().subsectionId().setValue(getIntent().getStringExtra(AppConstants.JSON_KEY_SUBSECTION_ID));
        getViewModel().sectionId().setValue(getIntent().getStringExtra(AppConstants.JSON_KEY_SECTION_ID));

        adapter = new QuizAdapter(getApplicationContext(), getController());
        rvAnswers.setAdapter(adapter);
        rvAnswers.setLayoutManager(new LinearLayoutManager(this));
    }

    @Override
    protected void observe(final QuizViewModel viewModel) {
        super.observe(viewModel);

        viewModel.currentQuestion().observe(this, new Observer<Integer>() {
            @Override
            public void onChanged(@Nullable Integer integer) {
                btnNext.setVisibility(View.VISIBLE);
            }
        });

        viewModel.showNext().observe(this, new Observer<Boolean>() {
            @Override
            public void onChanged(@Nullable Boolean aBoolean) {
                if (aBoolean) {
                    Integer integer = viewModel.currentQuestion().getValue();

                    if (integer < viewModel.questions().getValue().size()) {
                        showQuestion(integer);
                    } else {
                        ActivityUtility.invokeScoreActivity(QuizActivity.this, true,
                                getViewModel().topicId().getValue(),
                                getViewModel().subsectionId().getValue(),
                                getViewModel().sectionId().getValue(),
                                new ScoreModel(getViewModel().correctAnswers().getValue(),
                                        getViewModel().wrongAnswers().getValue(),
                                        getViewModel().skippedAnswers().getValue()));
                    }
                }
            }
        });
    }

    @Override
    protected void observeClicks(QuizController controller) {
        super.observeClicks(controller);

        btnNext.setOnClickListener(controller);
    }

    @SuppressLint("StringFormatMatches")
    private void showQuestion(Integer position) {
        btnNext.setVisibility(View.GONE);

        QuestionModel questionModel = getViewModel().questions().getValue().get(position);

        adapter.setData(questionModel.getAnswers());
        rvAnswers.setVisibility(View.VISIBLE);

        questionTitle.setText(String.format(getString(R.string.question_number), (getViewModel().currentQuestion().getValue() + 1)));
        questionText.setText(questionModel.getQuestion());
    }

    @Override
    protected QuizController createController(QuizViewModel viewModel) {
        QuizController quizController = new QuizController(viewModel);
        quizController.setContext(getApplicationContext());
        return quizController;
    }

    @Override
    protected Class<QuizViewModel> viewModel() {
        return QuizViewModel.class;
    }

    @Override
    public void onBackPressed() {
        ActivityUtility.invokeTopicActivity(QuizActivity.this, true,
                getViewModel().subsectionId().getValue(),
                getViewModel().sectionId().getValue());
    }
}
