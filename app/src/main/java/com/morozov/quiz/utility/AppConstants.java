package com.morozov.quiz.utility;

public class AppConstants {
    //image files
    public static final String IMAGE_DIR = "images/";
    public static final String JSON_KEY_IS_IMAGE_QUESTION = "is_image_question";
    public static final String JSON_KEY_IS_ANSWER_IMAGES = "is_image_answers";
    public static final String JSON_KEY_QUESTION_IMAGE = "question_image";
    public static final String JSON_KEY_ANSWER_IMAGES = "answer_images";

    // airplane file
    public static final String AIRPLANE_FILE = "json/airplanes/quiz_airplanes.json";
    public static final String JSON_KEY_ITEMS = "items";
    public static final String JSON_KEY_AIRPLANE_ID = "airplane";
    public static final String JSON_KEY_AIRPLANE_NAME = "airplane_name";

    // section file
    public static final String SECTION_FILE = "json/sections/quiz_sections.json";
    public static final String JSON_KEY_SECTION_ID = "question_section";
    public static final String JSON_KEY_SECTION_NAME = "section_name";

    // subsection file
    public static final String SUBSECTION_FILE = "json/subsections/quiz_subsections.json";
    public static final String JSON_KEY_SUBSECTION_ID = "question_subsection";
    public static final String JSON_KEY_SUBSECTION_NAME = "subsection_name";

    // topic file
    public static final String TOPIC_FILE = "json/topics/quiz_topics.json";
    public static final String JSON_KEY_TOPIC_ID = "question_topic";
    public static final String JSON_KEY_TOPIC_NAME = "topic_name";

    // question file
    public static final String QUESTION_FILE = "json/quiz/question_set.json";
    public static final String JSON_KEY_QUESTIONNAIRY = "questionnaires";
    public static final String JSON_KEY_QUESTION = "question";
    public static final String JSON_KEY_CORRECT_ANS = "correct_answer";
    public static final String JSON_KEY_ANSWERS = "answers";
    public static final String QUESTIONS_IN_TEST = "questions_count";

    // Bundle
    public static final String BUNDLE_KEY_INDEX = "index";
    public static final String BUNDLE_KEY_NAME = "name";
    public static final String BUNDLE_KEY_CURRENT_QUESTION = "current_question";
    public static final String BUNDLE_KEY_CORRECT_ANS = "correct_ans";
    public static final String BUNDLE_KEY_WRONG_ANS = "wrong_ans";
    public static final String BUNDLE_KEY_SKIPPED_ANS = "skipped_ans";
    public static final String BUNDLE_KEY_IS_TO_TEST = "is_to_test";
    public static final String BUNDLE_KEY_ACTIVITY_NAME = "activity_name";
    public static final int BUNDLE_KEY_ZERO_INDEX = 0;
    public static final int BUNDLE_KEY_FIRST_INDEX = 1;
    public static final int BUNDLE_KEY_SECOND_INDEX = 2;

    // pie chart constants
    public static final float TRANSPARENT_CIRCLE_RADIUS = 65f;
    public static final int ANIMATION_VALUE = 2000;

    // other
    public static final String EMPTY_STRING = "";

    // Shared Preference
    public static final String IS_LOGINED_ONCE = "is_logined_once";
}
