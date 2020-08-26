package com.morozov.quiz.controller.models;

import android.support.annotation.NonNull;

import io.realm.RealmObject;

public class TopicModelRealm extends RealmObject {
    public String topicName;
    public Integer percentage;

    public TopicModelRealm() {
    }

    public TopicModelRealm(@NonNull String topicName, @NonNull Integer percentage) {
        this.topicName = topicName;
        this.percentage = percentage;
    }
}
