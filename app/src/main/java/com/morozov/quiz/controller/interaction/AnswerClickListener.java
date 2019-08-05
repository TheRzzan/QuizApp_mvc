package com.morozov.quiz.controller.interaction;

import com.morozov.quiz.controller.models.MessageFromControllerModel;

public interface AnswerClickListener {
    MessageFromControllerModel onAnswerClicked(int position, String answer);
}
