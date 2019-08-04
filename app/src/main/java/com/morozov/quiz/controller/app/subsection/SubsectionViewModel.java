package com.morozov.quiz.controller.app.subsection;

import android.arch.lifecycle.MutableLiveData;

import com.morozov.quiz.controller.UiViewModel;
import com.morozov.quiz.controller.models.SubsectionModel;

import java.util.List;

public class SubsectionViewModel extends UiViewModel {
    private MutableLiveData<List<SubsectionModel>> subsections;
    private MutableLiveData<Integer> selectedSubsection;
    private MutableLiveData<String> sectionId;

    public SubsectionViewModel() {
        subsections = new MutableLiveData<>();
        selectedSubsection = new MutableLiveData<>();
        sectionId = new MutableLiveData<>();
    }

    MutableLiveData<List<SubsectionModel>> subsections() {
        return subsections;
    }

    MutableLiveData<Integer> selectedSubsection() {
        return selectedSubsection;
    }

    MutableLiveData<String> sectionId() {
        return sectionId;
    }
}
