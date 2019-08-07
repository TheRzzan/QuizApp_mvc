package com.morozov.quiz.controller.app.quiz;

import android.content.Context;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.Toast;

import com.morozov.quiz.R;
import com.morozov.quiz.controller.Controller;
import com.morozov.quiz.controller.interaction.AnswerClickListener;
import com.morozov.quiz.controller.models.MessageFromControllerModel;
import com.morozov.quiz.controller.models.QuestionModel;
import com.morozov.quiz.utility.DataLoader;

import java.util.ArrayList;
import java.util.Collections;

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
        ArrayList<QuestionModel> questions = DataLoader.getQuestions(context.getAssets(), viewModel().topicId().getValue());
        Collections.shuffle(questions);
        viewModel().questions().setValue(questions);
        viewModel().currentQuestion().setValue(0);
        viewModel().showNext().setValue(true);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.btn_next:
                viewModel().showNext().setValue(true);
                break;
        }
    }

    @Override
    public MessageFromControllerModel onAnswerClicked(int position, String answer) {
        if (viewModel().questions().click(position)) {
            QuestionModel questionModel = viewModel().questions().getValue().get(viewModel().currentQuestion().getValue());

            viewModel().currentQuestion().setValue(viewModel().currentQuestion().getValue() + 1);

            if (questionModel.getCorrectAnswer().equals(answer)) {
                Integer correct = viewModel().correctAnswers().getValue();
                viewModel().correctAnswers().setValue(correct + 1);
                return new MessageFromControllerModel(true, questionModel.getCorrectAnswer());
            } else {
                Integer wrong = viewModel().wrongAnswers().getValue();
                viewModel().wrongAnswers().setValue(wrong + 1);
                return new MessageFromControllerModel(false, questionModel.getCorrectAnswer());
            }
        } else {
            if (viewModel().currentQuestion().getValue().equals(0)) {
                View toastView = LayoutInflater.from(context).inflate(R.layout.toast_custom_view, null);

                Toast toast = new Toast(context);
                toast.setView(toastView);
                toast.setDuration(Toast.LENGTH_LONG);
                toast.setGravity(Gravity.BOTTOM,0,75);
                toast.show();
            }

            return null;
        }
    }
}
