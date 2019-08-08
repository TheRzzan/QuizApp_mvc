package com.morozov.quiz.controller.app.quiz;

import android.annotation.SuppressLint;
import android.arch.lifecycle.Observer;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.ActionBar;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import com.morozov.quiz.R;
import com.morozov.quiz.controller.ControllerActivity;
import com.morozov.quiz.controller.interaction.DialogClickListener;
import com.morozov.quiz.controller.models.QuestionModel;
import com.morozov.quiz.controller.models.ScoreModel;
import com.morozov.quiz.controller.ui.CustomDialog;
import com.morozov.quiz.utility.ActivityTitles;
import com.morozov.quiz.utility.ActivityUtility;
import com.morozov.quiz.utility.AppConstants;

import java.io.IOException;
import java.io.InputStream;

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

        getViewModel().topicId().setValue(getIntent().getStringExtra(AppConstants.JSON_KEY_TOPIC_ID));
        getViewModel().subsectionId().setValue(getIntent().getStringExtra(AppConstants.JSON_KEY_SUBSECTION_ID));
        getViewModel().sectionId().setValue(getIntent().getStringExtra(AppConstants.JSON_KEY_SECTION_ID));

        adapter = new QuizAdapter(getApplicationContext(), getController());
        rvAnswers.setAdapter(adapter);
        rvAnswers.setLayoutManager(new LinearLayoutManager(this));
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

        if (questionModel.isImageAnswer()) {
            adapter.setImageAnswer(true);
            adapter.setData(questionModel.getAnswerImages());
        } else {
            adapter.setImageAnswer(false);
            adapter.setData(questionModel.getAnswers());
        }

        rvAnswers.setVisibility(View.VISIBLE);

        questionTitle.setText(String.format(getString(R.string.question_number), (getViewModel().currentQuestion().getValue() + 1)));
        questionText.setText(questionModel.getQuestion());

        if (questionModel.isImageQuestion()) {
            ivQuestion.setVisibility(View.VISIBLE);

            InputStream inputStream = null;
            try{
                inputStream = ivQuestion.getContext().getAssets().open(AppConstants.IMAGE_DIR + questionModel.getQuestionImage());
                Drawable d = Drawable.createFromStream(inputStream, null);
                ivQuestion.setImageDrawable(d);
                ivQuestion.setScaleType(ImageView.ScaleType.FIT_XY);
            }
            catch (IOException e){
                e.printStackTrace();
            }
            finally {
                try{
                    if(inputStream!=null)
                        inputStream.close();
                }
                catch (IOException ex){
                    ex.printStackTrace();
                }
            }
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
        ActivityUtility.invokeTopicActivity(QuizActivity.this, true,
                getViewModel().subsectionId().getValue(),
                getViewModel().sectionId().getValue(),
                true);
    }
}
