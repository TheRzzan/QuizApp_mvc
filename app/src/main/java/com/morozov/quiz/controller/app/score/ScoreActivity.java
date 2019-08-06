package com.morozov.quiz.controller.app.score;

import android.arch.lifecycle.Observer;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.widget.Toolbar;
import android.widget.Button;
import android.widget.TextView;

import com.morozov.quiz.R;
import com.morozov.quiz.controller.ControllerActivity;
import com.morozov.quiz.controller.app.section.SectionActivity;
import com.morozov.quiz.controller.interaction.DialogClickListener;
import com.morozov.quiz.controller.models.ScoreModel;
import com.morozov.quiz.controller.ui.CustomDialog;
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

        getViewModel().score().setValue(
                new ScoreModel(getIntent().getIntExtra(AppConstants.BUNDLE_KEY_CORRECT_ANS, 0),
                        getIntent().getIntExtra(AppConstants.BUNDLE_KEY_WRONG_ANS, 0),
                        getIntent().getIntExtra(AppConstants.BUNDLE_KEY_SKIPPED_ANS, 0))
        );

        getViewModel().topicId().setValue(getIntent().getStringExtra(AppConstants.JSON_KEY_TOPIC_ID));
        getViewModel().subsectionId().setValue(getIntent().getStringExtra(AppConstants.JSON_KEY_SUBSECTION_ID));
        getViewModel().sectionId().setValue(getIntent().getStringExtra(AppConstants.JSON_KEY_SECTION_ID));
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
                    customDialog.setHeadline("Начать тест заново?");
                    customDialog.setListener(ScoreActivity.this);
                    customDialog.show(getSupportFragmentManager(), "CustomDialog");
                }
            }
        });

        viewModel.startMainMenu().observe(this, new Observer<Boolean>() {
            @Override
            public void onChanged(@Nullable Boolean aBoolean) {
                if (aBoolean) {
                    ActivityUtility.invokeNewActivity(ScoreActivity.this, SectionActivity.class, true);
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
        ActivityUtility.invokeTopicActivity(ScoreActivity.this, true,
                getViewModel().subsectionId().getValue(),
                getViewModel().sectionId().getValue());
    }

    @Override
    public void onCancelClicked() {

    }

    @Override
    public void onOkClicked() {
        ActivityUtility.invokeQuizActivity(ScoreActivity.this, true,
                getViewModel().topicId().getValue(),
                getViewModel().subsectionId().getValue(),
                getViewModel().sectionId().getValue());
    }
}
