package com.morozov.quiz.controller.app.score;

import android.arch.lifecycle.Observer;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.widget.Toolbar;
import android.widget.Button;
import android.widget.TextView;

import com.github.mikephil.charting.charts.PieChart;
import com.github.mikephil.charting.data.Entry;
import com.github.mikephil.charting.data.PieData;
import com.github.mikephil.charting.data.PieDataSet;
import com.github.mikephil.charting.formatter.PercentFormatter;
import com.github.mikephil.charting.utils.ColorTemplate;
import com.morozov.quiz.R;
import com.morozov.quiz.controller.ControllerActivity;
import com.morozov.quiz.controller.app.section.SectionActivity;
import com.morozov.quiz.controller.models.ScoreModel;
import com.morozov.quiz.utility.ActivityUtility;
import com.morozov.quiz.utility.AppConstants;

import java.util.ArrayList;

import butterknife.BindView;
import butterknife.ButterKnife;

public class ScoreActivity extends ControllerActivity<ScoreViewModel, ScoreController> {

    @BindView(R.id.toolbar)
    Toolbar toolbar;

    @BindView(R.id.btn_main_menu)
    Button btnMainMenu;

    @BindView(R.id.btn_play_again)
    Button btnPlayAgain;

    @BindView(R.id.txt_score)
    TextView txtScore;

    @BindView(R.id.txt_wrong)
    TextView txtWrong;

    @BindView(R.id.txt_skip)
    TextView txtSkip;

    @BindView(R.id.piechart)
    PieChart pcScore;

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
                    ActivityUtility.invokeQuizActivity(ScoreActivity.this, true,
                            getViewModel().topicId().getValue(),
                            getViewModel().subsectionId().getValue(),
                            getViewModel().sectionId().getValue());
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

        btnMainMenu.setOnClickListener(controller);
        btnPlayAgain.setOnClickListener(controller);
    }

    private void showScore(ScoreModel score) {
        txtScore.setText(String.valueOf(score.getCorrectAnswers()));
        txtWrong.setText(String.valueOf(score.getWrongAnswers()));
        txtSkip. setText(String.valueOf(score.getSkippedAnswers()));

        showPieChart(score.getCorrectAnswers(),
                score.getWrongAnswers(),
                score.getSkippedAnswers());
    }

    public void showPieChart(int mScore, int mWrongAns, int mSkip) {
        pcScore.setUsePercentValues(true);
        pcScore.setDrawHoleEnabled(true);
        pcScore.setTransparentCircleRadius(AppConstants.TRANSPARENT_CIRCLE_RADIUS);
        pcScore.setHoleRadius(AppConstants.TRANSPARENT_CIRCLE_RADIUS);
        pcScore.setDescription("Результаты");
        pcScore.animateXY(AppConstants.ANIMATION_VALUE, AppConstants.ANIMATION_VALUE);

        ArrayList<Entry> yvalues = new ArrayList<Entry>();
        yvalues.add(new Entry(mScore, AppConstants.BUNDLE_KEY_ZERO_INDEX));
        yvalues.add(new Entry(mWrongAns, AppConstants.BUNDLE_KEY_SECOND_INDEX));
        yvalues.add(new Entry(mSkip, AppConstants.BUNDLE_KEY_FIRST_INDEX));
        PieDataSet dataSet = new PieDataSet(yvalues, AppConstants.EMPTY_STRING);
        dataSet.setColors(ColorTemplate.JOYFUL_COLORS);

        ArrayList<String> xVals = new ArrayList<String>();
        xVals.add("Правильно");
        xVals.add("Неправильно");
        xVals.add("Пропущено");
        PieData data = new PieData(xVals, dataSet);

        data.setValueFormatter(new PercentFormatter());
        pcScore.setData(data);
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
}
