package com.morozov.quiz.controller.models;

public class ErrorModel {

    private final String title;
    private final String body;

    public String title() {
        return title;
    }

    public String body() {
        return body;
    }

    public ErrorModel(String title, String body) {
        this.title = title;
        this.body = body;
    }
}
