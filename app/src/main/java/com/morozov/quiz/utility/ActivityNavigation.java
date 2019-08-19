package com.morozov.quiz.utility;

import android.content.Context;

public class ActivityNavigation {

    private static ActivityNavigation instance;
    private Context context;

    private String airplaneId;
    private String sectionId;
    private String subsectionId;
    private String topicId;

    private ActivityNavigation(Context context) {
        this.context = context;
    }

    public static ActivityNavigation getInstance(Context context) {
        if (instance == null)
            instance = new ActivityNavigation(context);
        return instance;
    }

    public void setAirplaneId(String airplaneId) {
        this.airplaneId = airplaneId;
    }

    public void setSectionId(String sectionId) {
        this.sectionId = sectionId;
    }

    public void setSubsectionId(String subsectionId) {
        this.subsectionId = subsectionId;
    }

    public void setTopicId(String topicId) {
        this.topicId = topicId;
    }

    public String getAirplaneId() {
        if (airplaneId == null)
            airplaneId = "0";

        return airplaneId;
    }

    public String getSectionId() {
        if (sectionId == null)
            sectionId = "0";

        return sectionId;
    }

    public String getSubsectionId() {
        if (subsectionId == null)
            subsectionId = "0";

        return subsectionId;
    }

    public String getTopicId() {
        if (topicId == null)
            topicId = "0";

        return topicId;
    }
}
