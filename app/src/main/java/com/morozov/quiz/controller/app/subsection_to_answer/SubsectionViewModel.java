package com.morozov.quiz.controller.app.subsection_to_answer;

import androidx.lifecycle.MutableLiveData;

import com.morozov.quiz.controller.UiViewModel;
import com.morozov.quiz.controller.models.SubsectionModel;

import java.util.List;

public class SubsectionViewModel extends UiViewModel {
    private MutableLiveData<List<SubsectionModel>> subsections;

    private MutableLiveData<Integer> selectedSubsection;
    private MutableLiveData<Integer> selectedSection;

    private MutableLiveData<String> subsectionName;

    public SubsectionViewModel() {
        subsections = new MutableLiveData<>();

        selectedSubsection = new MutableLiveData<>();
        selectedSection = new MutableLiveData<>();

        subsectionName = new MutableLiveData<>();
    }

    MutableLiveData<List<SubsectionModel>> subsections() {
        return subsections;
    }

    MutableLiveData<Integer> selectedSubsection() {
        return selectedSubsection;
    }

    MutableLiveData<Integer> selectedSection() {
        return selectedSection;
    }

    MutableLiveData<String> subsectionName() {
        return subsectionName;
    }
}
