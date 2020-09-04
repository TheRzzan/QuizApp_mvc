package com.morozov.quiz.controller.app.airplane;

import androidx.lifecycle.MutableLiveData;

import com.morozov.quiz.controller.UiViewModel;
import com.morozov.quiz.controller.models.AirplaneModel;

import java.util.List;

public class AirplaneViewModel extends UiViewModel {
    private MutableLiveData<List<AirplaneModel>> airplanes;
    private MutableLiveData<Integer> selectedAirplane;

    public AirplaneViewModel() {
        airplanes = new MutableLiveData<>();
        selectedAirplane = new MutableLiveData<>();
    }

    MutableLiveData<List<AirplaneModel>> airplanes() {
        return airplanes;
    }

    MutableLiveData<Integer> selectedAirplane() {
        return selectedAirplane;
    }
}
