package com.morozov.quiz.controller.app.topic;

import android.arch.lifecycle.MutableLiveData;

import com.morozov.quiz.controller.UiViewModel;
import com.morozov.quiz.controller.models.TopicModel;

import java.util.List;

public class TopicViewModel extends UiViewModel {
    private MutableLiveData<List<TopicModel>> topics;
    private MutableLiveData<Integer> selectedTopic;
    private MutableLiveData<String> subsectionId;
    private MutableLiveData<String> sectionId;
    private Boolean isToTest;

    public TopicViewModel() {
        topics = new MutableLiveData<>();
        selectedTopic = new MutableLiveData<>();
        subsectionId = new MutableLiveData<>();
        sectionId = new MutableLiveData<>();
    }

    MutableLiveData<List<TopicModel>> topics() {
        return topics;
    }

    MutableLiveData<Integer> selectedTopic() {
        return selectedTopic;
    }

    MutableLiveData<String> subsectionId() {
        return subsectionId;
    }

    MutableLiveData<String> sectionId() {
        return sectionId;
    }

    Boolean isToTest() {
        return isToTest;
    }

    void setIsToTest(Boolean toTest) {
        isToTest = toTest;
    }
}
