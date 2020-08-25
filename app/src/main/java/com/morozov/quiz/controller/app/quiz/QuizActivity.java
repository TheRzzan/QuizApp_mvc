package com.morozov.quiz.controller.app.quiz;

import android.annotation.SuppressLint;
import android.arch.lifecycle.Observer;
import android.os.Bundle;
import android.os.PersistableBundle;
import android.support.annotation.Nullable;
import android.support.v7.app.ActionBar;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.text.Html;
import android.view.MenuItem;
import android.view.View;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import com.morozov.quiz.R;
import com.morozov.quiz.controller.ControllerActivity;
import com.morozov.quiz.controller.app.topic.TopicActivity;
import com.morozov.quiz.controller.interaction.DialogClickListener;
import com.morozov.quiz.controller.models.QuestionModel;
import com.morozov.quiz.controller.models.ScoreModel;
import com.morozov.quiz.controller.ui.CustomDialog;
import com.morozov.quiz.controller.ui.ImageDialog;
import com.morozov.quiz.utility.ActivityTitles;
import com.morozov.quiz.utility.ActivityUtility;
import com.morozov.quiz.utility.AppConstants;
import com.morozov.quiz.utility.DataLoader;

import butterknife.BindView;
import butterknife.ButterKnife;

public class QuizActivity extends ControllerActivity<QuizViewModel, QuizController> implements DialogClickListener {

    @BindView(R.id.toolbar)
    Toolbar toolbar;

    @BindView(R.id.rvAnswers)
    RecyclerView rvAnswers;

    @BindView(R.id.tvQuestionTitle)
    TextView questionTitle;

    @BindView(R.id.tvQuestionText)
    TextView questionText;

    @BindView(R.id.ivQuestion)
    ImageView ivQuestion;

    @BindView(R.id.btn_next)
    Button btnNext;

    private QuizAdapter adapter;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_quiz);
        ButterKnife.bind(this);

        setSupportActionBar(toolbar);
        ActionBar actionBar = getSupportActionBar();
        if (actionBar != null) {
            actionBar.setTitle(ActivityTitles.getInstance(getApplicationContext()).getTopicName());
            actionBar.setDisplayHomeAsUpEnabled(true);
        }

        adapter = new QuizAdapter(getApplicationContext(), getController(), getSupportFragmentManager());
        rvAnswers.setAdapter(adapter);
        rvAnswers.setLayoutManager(new LinearLayoutManager(this));

        getWindow().setSoftInputMode(WindowManager.LayoutParams.SOFT_INPUT_STATE_HIDDEN);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        if (item.getItemId() == android.R.id.home)
            this.onBackPressed();

        return super.onOptionsItemSelected(item);
    }

    @Override
    protected void observe(final QuizViewModel viewModel) {
        super.observe(viewModel);

        viewModel.currentQuestion().observe(this, new Observer<Integer>() {
            @Override
            public void onChanged(@Nullable Integer integer) {
                if (integer.equals(viewModel.questions().getValue().size()))
                    btnNext.setText(getString(R.string.finish));

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
        rvAnswers.scrollToPosition(0);

        btnNext.setVisibility(View.GONE);

        QuestionModel questionModel = getViewModel().questions().getValue().get(position);

        if (questionModel.isImageAnswer()) {
            adapter.setImageAnswer(true);
            adapter.setData(questionModel.getAnswerImages());
        } else {
            adapter.setImageAnswer(false);
            adapter.setData(questionModel.getAnswers());
        }

        questionTitle.setText(String.format(getString(R.string.question_number), (getViewModel().currentQuestion().getValue() + 1)));
        questionText.setText(Html.fromHtml(questionModel.getQuestion()));

        if (questionModel.isImageQuestion()) {
            ivQuestion.setVisibility(View.VISIBLE);
            ivQuestion.setImageDrawable(DataLoader.loadImage(getApplicationContext(), questionModel.getQuestionImage()));
            ivQuestion.setScaleType(ImageView.ScaleType.FIT_XY);
            ivQuestion.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    ImageDialog imageDialog = new ImageDialog();
                    imageDialog.setImage(DataLoader.loadImage(
                            getApplicationContext(),
                            questionModel.getQuestionImage()
                    ));
                    imageDialog.show(getSupportFragmentManager(), QuizActivity.class.getSimpleName());
                }
            });
        } else {
            ivQuestion.setVisibility(View.GONE);
        }
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
        CustomDialog customDialog = new CustomDialog();
        customDialog.setHeadline(getString(R.string.leave_testing));
        customDialog.setListener(QuizActivity.this);
        customDialog.setOkText(getString(R.string.leave));
        customDialog.show(getSupportFragmentManager(), CustomDialog.class.getSimpleName());
    }

    @Override
    public void onCancelClicked() {

    }

    @Override
    public void onOkClicked() {
        ActivityUtility.invokeNewActivity(QuizActivity.this, TopicActivity.class, true);
    }

    @Override
    protected void onSaveInstanceState(Bundle outState) {
        super.onSaveInstanceState(outState);
        QuizViewModel viewModel = getViewModel();

        outState.putInt(AppConstants.BUNDLE_KEY_CURRENT_QUESTION, viewModel.currentQuestion().getValue());
        outState.putInt(AppConstants.BUNDLE_KEY_CORRECT_ANS, viewModel.correctAnswers().getValue());
        outState.putInt(AppConstants.BUNDLE_KEY_WRONG_ANS, viewModel.wrongAnswers().getValue());
        outState.putInt(AppConstants.BUNDLE_KEY_SKIPPED_ANS, viewModel.skippedAnswers().getValue());
    }

    @Override
    public void onRestoreInstanceState(Bundle savedInstanceState, PersistableBundle persistentState) {
        super.onRestoreInstanceState(savedInstanceState, persistentState);
        QuizViewModel viewModel = getViewModel();

        viewModel.currentQuestion().setValue(savedInstanceState.getInt(AppConstants.BUNDLE_KEY_CURRENT_QUESTION));
        viewModel.correctAnswers().setValue(savedInstanceState.getInt(AppConstants.BUNDLE_KEY_CORRECT_ANS));
        viewModel.wrongAnswers().setValue(savedInstanceState.getInt(AppConstants.BUNDLE_KEY_WRONG_ANS));
        viewModel.skippedAnswers().setValue(savedInstanceState.getInt(AppConstants.BUNDLE_KEY_SKIPPED_ANS));
    }
}
