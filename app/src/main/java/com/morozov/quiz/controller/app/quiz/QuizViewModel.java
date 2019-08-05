package com.morozov.quiz.controller.app.quiz;

import android.arch.lifecycle.MutableLiveData;

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

    private MutableLiveData<String> topicId;
    private MutableLiveData<String> subsectionId;
    private MutableLiveData<String> sectionId;

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

        topicId = new MutableLiveData<>();
        subsectionId = new MutableLiveData<>();
        sectionId = new MutableLiveData<>();
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

    MutableLiveData<String> topicId() {
        return topicId;
    }
    MutableLiveData<String> subsectionId() {
        return subsectionId;
    }
    MutableLiveData<String> sectionId() {
        return sectionId;
    }
}
