package com.morozov.quiz.controller.models;

public class AirplaneModel {
    private String airplaneId;
    private String airplaneName;

    public AirplaneModel(String airplaneId, String airplaneName) {
        this.airplaneId = airplaneId;
        this.airplaneName = airplaneName;
    }

    public String getAirplaneId() {
        return airplaneId;
    }

    public String getAirplaneName() {
        return airplaneName;
    }
}
