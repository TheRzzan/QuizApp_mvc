package com.morozov.quiz.controller.app.answer;

import android.arch.lifecycle.MutableLiveData;

import com.morozov.quiz.controller.UiViewModel;
import com.morozov.quiz.controller.models.QuestionModel;

import java.util.List;

public class AnswerViewModel extends UiViewModel {
    private MutableLiveData<List<QuestionModel>> questions;

    private MutableLiveData<String> topicId;
    private MutableLiveData<String> subsectionId;
    private MutableLiveData<String> sectionId;

    public AnswerViewModel() {
        questions = new MutableLiveData<>();

        topicId = new MutableLiveData<>();
        subsectionId = new MutableLiveData<>();
        sectionId = new MutableLiveData<>();
    }

    MutableLiveData<List<QuestionModel>> questions() {
        return questions;
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
