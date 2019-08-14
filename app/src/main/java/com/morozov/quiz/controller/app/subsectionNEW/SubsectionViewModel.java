package com.morozov.quiz.controller.app.subsectionNEW;

import android.arch.lifecycle.MutableLiveData;

import com.morozov.quiz.controller.UiViewModel;
import com.morozov.quiz.controller.models.SectionModel;

import java.util.List;

public class SubsectionViewModel extends UiViewModel {
    private MutableLiveData<List<SectionModel>> sections;
    private MutableLiveData<Integer> selectedSubsection;
    private MutableLiveData<Integer> selectedSection;

    private MutableLiveData<String> subsectionName;

    public SubsectionViewModel() {
        sections = new MutableLiveData<>();
        selectedSubsection = new MutableLiveData<>();
        selectedSection = new MutableLiveData<>();

        subsectionName = new MutableLiveData<>();
    }

    MutableLiveData<List<SectionModel>> sections() {
        return sections;
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
