package com.morozov.quiz.controller.app.answer;

import android.arch.lifecycle.MutableLiveData;

import com.morozov.quiz.controller.UiViewModel;
import com.morozov.quiz.controller.models.QuestionModel;
import com.morozov.quiz.controller.models.TopicModel;

import java.util.List;

public class AnswerViewModel extends UiViewModel {
    private MutableLiveData<List<TopicModel>> topics;

    public AnswerViewModel() {
        topics = new MutableLiveData<>();
    }

    MutableLiveData<List<TopicModel>> topics() {
        return topics;
    }
}
