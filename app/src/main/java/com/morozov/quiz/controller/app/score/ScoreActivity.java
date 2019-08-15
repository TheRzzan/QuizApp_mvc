package com.morozov.quiz.controller.app.score;

import android.arch.lifecycle.Observer;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.ActionBar;
import android.support.v7.widget.Toolbar;
import android.view.MenuItem;
import android.widget.Button;
import android.widget.TextView;

import com.morozov.quiz.R;
import com.morozov.quiz.controller.ControllerActivity;
import com.morozov.quiz.controller.app.quiz.QuizActivity;
import com.morozov.quiz.controller.app.subsection.SubsectionActivity;
import com.morozov.quiz.controller.app.topic.TopicActivity;
import com.morozov.quiz.controller.interaction.DialogClickListener;
import com.morozov.quiz.controller.models.ScoreModel;
import com.morozov.quiz.controller.ui.CustomDialog;
import com.morozov.quiz.utility.ActivityTitles;
import com.morozov.quiz.utility.ActivityUtility;
import com.morozov.quiz.utility.AppConstants;
import com.morozov.quiz.utility.PieChart;

import butterknife.BindView;
import butterknife.ButterKnife;

public class ScoreActivity extends ControllerActivity<ScoreViewModel, ScoreController> implements DialogClickListener {

    @BindView(R.id.toolbar)
    Toolbar toolbar;

    @BindView(R.id.btn_play_again)
    Button btnPlayAgain;

    @BindView(R.id.tvTotalQuestions)
    TextView tvTotalQuestions;

    @BindView(R.id.piechartCorrect)
    PieChart piechartCorrect;

    @BindView(R.id.piechartWrong)
    PieChart piechartWrong;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_score);
        ButterKnife.bind(this);

        setSupportActionBar(toolbar);
        ActionBar actionBar = getSupportActionBar();
        if (actionBar != null) {
            actionBar.setTitle(ActivityTitles.getInstance(getApplicationContext()).getTopicName());
            actionBar.setDisplayHomeAsUpEnabled(true);
        }

        getViewModel().score().setValue(
                new ScoreModel(getIntent().getIntExtra(AppConstants.BUNDLE_KEY_CORRECT_ANS, 0),
                        getIntent().getIntExtra(AppConstants.BUNDLE_KEY_WRONG_ANS, 0),
                        getIntent().getIntExtra(AppConstants.BUNDLE_KEY_SKIPPED_ANS, 0))
        );
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        if (item.getItemId() == android.R.id.home)
            this.onBackPressed();

        return super.onOptionsItemSelected(item);
    }

    @Override
    protected void observe(ScoreViewModel viewModel) {
        super.observe(viewModel);

        viewModel.showScore().observe(this, new Observer<Boolean>() {
            @Override
            public void onChanged(@Nullable Boolean aBoolean) {
                if (aBoolean) {
                    showScore(getViewModel().score().getValue());
                }
            }
        });

        viewModel.startTestAgain().observe(this, new Observer<Boolean>() {
            @Override
            public void onChanged(@Nullable Boolean aBoolean) {
                if (aBoolean) {
                    CustomDialog customDialog = new CustomDialog();
                    customDialog.setHeadline(getString(R.string.start_test_again));
                    customDialog.setListener(ScoreActivity.this);
                    customDialog.show(getSupportFragmentManager(), CustomDialog.class.getSimpleName());
                }
            }
        });

        viewModel.startMainMenu().observe(this, new Observer<Boolean>() {
            @Override
            public void onChanged(@Nullable Boolean aBoolean) {
                if (aBoolean) {
                    ActivityUtility.invokeNewActivity(ScoreActivity.this, SubsectionActivity.class, true);
                }
            }
        });
    }

    @Override
    protected void observeClicks(ScoreController controller) {
        super.observeClicks(controller);

//        btnMainMenu.setOnClickListener(controller);
        btnPlayAgain.setOnClickListener(controller);
    }

    private void showScore(ScoreModel score) {
        tvTotalQuestions.setText(String.valueOf(
                score.getCorrectAnswers() +
                score.getWrongAnswers() +
                score.getSkippedAnswers()
        ));

        showPieCharts(score.getCorrectAnswers(),
                score.getWrongAnswers(),
                score.getSkippedAnswers());
    }

    private void showPieCharts(int mScore, int mWrongAns, int mSkip) {
        piechartCorrect.setItem(new float[]{mScore,mWrongAns});
        piechartCorrect.setTotal(mScore);
        piechartCorrect.setColors(new String[]{"#4CD964", "#5B6064"});
        piechartCorrect.notifyDraw();

        piechartWrong.setItem(new float[]{mScore,mWrongAns});
        piechartWrong.setTotal(mWrongAns);
        piechartWrong.setColors(new String[]{"#5B6064", "#D94C4C"});
        piechartWrong.notifyDraw();
    }

    @Override
    protected ScoreController createController(ScoreViewModel viewModel) {
        ScoreController scoreController = new ScoreController(viewModel);
        scoreController.setContext(getApplicationContext());
        return scoreController;
    }

    @Override
    protected Class<ScoreViewModel> viewModel() {
        return ScoreViewModel.class;
    }

    @Override
    public void onBackPressed() {
        ActivityUtility.invokeNewActivity(ScoreActivity.this, TopicActivity.class, true);
    }

    @Override
    public void onCancelClicked() {

    }

    @Override
    public void onOkClicked() {
        ActivityUtility.invokeNewActivity(ScoreActivity.this, QuizActivity.class, true);
    }
}
