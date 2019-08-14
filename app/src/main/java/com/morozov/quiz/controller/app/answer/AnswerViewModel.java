package com.morozov.quiz.controller.app.answer;

import android.arch.lifecycle.MutableLiveData;

import com.morozov.quiz.controller.UiViewModel;
import com.morozov.quiz.controller.models.QuestionModel;

import java.util.List;

public class AnswerViewModel extends UiViewModel {
    private MutableLiveData<List<QuestionModel>> questions;

    public AnswerViewModel() {
        questions = new MutableLiveData<>();
    }

    MutableLiveData<List<QuestionModel>> questions() {
        return questions;
    }
}
