package com.morozov.quiz.controller.app.quiz;

import android.content.Context;
import android.view.View;

import com.morozov.quiz.controller.Controller;
import com.morozov.quiz.controller.interaction.AnswerClickListener;
import com.morozov.quiz.controller.models.QuestionModel;
import com.morozov.quiz.utility.DataLoader;

public class QuizController extends Controller<QuizViewModel> implements View.OnClickListener, AnswerClickListener {

    private Context context;

    public QuizController(QuizViewModel viewModel) {
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
        viewModel().questions()
                .setValue(DataLoader.getQuestions(context.getAssets(), viewModel().topicId().getValue()));
        viewModel().currentQuestion().setValue(0);
    }

    private void clickProcessing(Integer position) {

    }

    @Override
    public void onClick(View v) {
        clickProcessing((Integer) v.getTag());
    }

    @Override
    public void onAnswerClicked(int position, String answer) {
        if (viewModel().questions().click(position)) {
            QuestionModel questionModel = viewModel().questions().getValue().get(position);

            if (questionModel.getCorrectAnswer().equals(answer)) {
                Integer correct = viewModel().correctAnswers().getValue();
                viewModel().correctAnswers().setValue(correct + 1);
            } else {
                Integer wrong = viewModel().wrongAnswers().getValue();
                viewModel().wrongAnswers().setValue(wrong + 1);
            }

            viewModel().currentQuestion().setValue(viewModel().currentQuestion().getValue() + 1);
        }
    }
}
