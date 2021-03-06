package com.morozov.quiz.controller.app.quiz;

import android.annotation.SuppressLint;
import androidx.lifecycle.Observer;

import android.os.Bundle;
import android.os.PersistableBundle;
import androidx.annotation.Nullable;
import androidx.appcompat.app.ActionBar;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import androidx.appcompat.widget.Toolbar;
import android.text.Html;
import android.util.Pair;
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
import com.morozov.quiz.controller.models.TopicModelRealm;
import com.morozov.quiz.controller.ui.CustomDialog;
import com.morozov.quiz.controller.ui.ImageDialog;
import com.morozov.quiz.utility.ActivityTitles;
import com.morozov.quiz.utility.ActivityUtility;
import com.morozov.quiz.utility.AppConstants;
import com.morozov.quiz.utility.DataLoader;

import java.util.ArrayList;

import butterknife.BindView;
import butterknife.ButterKnife;
import io.realm.Realm;

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
                        Integer correctAns = getViewModel().correctAnswers().getValue();
                        Integer wrongAns = getViewModel().wrongAnswers().getValue();
                        Realm.getDefaultInstance().executeTransaction( realm -> {
                            String topicName = ActivityTitles.getInstance(getApplicationContext()).getTopicName();
                            realm.where(TopicModelRealm.class).equalTo("topicName", topicName).findAll().deleteAllFromRealm();
                            TopicModelRealm topicModelRealm = realm.createObject(TopicModelRealm.class);
                            topicModelRealm.topicName = topicName;
                            topicModelRealm.percentage = (correctAns * 100)/(correctAns + wrongAns);
                        });
                        ActivityUtility.invokeScoreActivity(QuizActivity.this, true,
                                new ScoreModel(correctAns,
                                        wrongAns,
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
            ArrayList<Pair<String, String>> imagesData = new ArrayList<>();
            for (String item : questionModel.getAnswerImages()) {
                imagesData.add(Pair.create(null, item));
            }
            adapter.setData(imagesData);
        } else {
            adapter.setImageAnswer(false);
            ArrayList<Pair<String, String>> textData = new ArrayList<>();
            ArrayList<String> answers = questionModel.getAnswers();
            for (int i = 0; i < answers.size(); i++) {
                String imageAdd;
                if (questionModel.getAnswerImages().size() > i) {
                    imageAdd = questionModel.getAnswerImages().get(i);
                } else {
                    imageAdd = null;
                }
                String item = answers.get(i);
                textData.add(Pair.create(item, imageAdd));
            }
            adapter.setData(textData);
        }

        questionTitle.setText(String.format(getString(R.string.question_number), (getViewModel().currentQuestion().getValue() + 1)));
        String text = questionModel.getQuestion().replace("\n", "<br>");
        questionText.setText(Html.fromHtml(text));

        if (questionModel.isImageQuestion()) {
            ivQuestion.setVisibility(View.VISIBLE);
            ivQuestion.setImageDrawable(DataLoader.loadImage(getApplicationContext(), questionModel.getQuestionImage()));
            ivQuestion.setScaleType(ImageView.ScaleType.FIT_XY);
            ivQuestion.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    ImageDialog imageDialog = new ImageDialog();
                    imageDialog.setImage(AppConstants.IMAGE_DIR + questionModel.getQuestionImage());
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
//        customDialog.setListener(QuizActivity.this);
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
