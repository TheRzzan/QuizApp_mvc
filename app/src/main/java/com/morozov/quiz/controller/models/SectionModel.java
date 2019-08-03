package com.morozov.quiz.controller.models;

public class SectionModel {
    private String sectionId;
    private String sectionName;

    public SectionModel(String sectionId, String sectionName) {
        this.sectionId = sectionId;
        this.sectionName = sectionName;
    }

    public String getSectionId() {
        return sectionId;
    }

    public String getSectionName() {
        return sectionName;
    }
}
