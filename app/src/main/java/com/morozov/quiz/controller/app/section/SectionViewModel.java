package com.morozov.quiz.controller.app.section;

import android.arch.lifecycle.MutableLiveData;

import com.morozov.quiz.controller.UiViewModel;
import com.morozov.quiz.controller.models.SectionModel;

import java.util.List;

public class SectionViewModel extends UiViewModel {
    private MutableLiveData<List<SectionModel>> sections;
    private MutableLiveData<Integer> selectedSection;

    public SectionViewModel() {
        sections = new MutableLiveData<>();
        selectedSection = new MutableLiveData<>();
    }

    public MutableLiveData<List<SectionModel>> sections() {
        return sections;
    }

    public MutableLiveData<Integer> selectedSection() {
        return selectedSection;
    }
}
