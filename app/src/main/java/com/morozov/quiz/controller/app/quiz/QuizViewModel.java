package com.morozov.quiz.controller.app.quiz;

import androidx.lifecycle.MutableLiveData;

import com.morozov.quiz.controller.UiViewModel;
import com.morozov.quiz.controller.models.DoubleClickLiveData;
import com.morozov.quiz.controller.models.QuestionModel;

import java.util.List;

public class QuizViewModel extends UiViewModel {
    private DoubleClickLiveData<List<QuestionModel>> questions;

    private MutableLiveData<Boolean> showNext;
    private MutableLiveData<Integer> currentQuestion;

    private MutableLiveData<Integer> correctAnswers;
    private MutableLiveData<Integer> wrongAnswers;
    private MutableLiveData<Integer> skippedAnswers;

    public QuizViewModel() {
        questions = new DoubleClickLiveData<>();

        showNext = new MutableLiveData<>();
        currentQuestion = new MutableLiveData<>();

        correctAnswers = new MutableLiveData<>();
        correctAnswers.setValue(0);
        wrongAnswers = new MutableLiveData<>();
        wrongAnswers.setValue(0);
        skippedAnswers = new MutableLiveData<>();
        skippedAnswers.setValue(0);
    }

    DoubleClickLiveData<List<QuestionModel>> questions() {
        return questions;
    }

    MutableLiveData<Boolean> showNext() {
        return showNext;
    }
    MutableLiveData<Integer> currentQuestion() {
        return currentQuestion;
    }

    MutableLiveData<Integer> correctAnswers() {
        return correctAnswers;
    }
    MutableLiveData<Integer> wrongAnswers() {
        return wrongAnswers;
    }
    MutableLiveData<Integer> skippedAnswers() {
        return skippedAnswers;
    }
}
