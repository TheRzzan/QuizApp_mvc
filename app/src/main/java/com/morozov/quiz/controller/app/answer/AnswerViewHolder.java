package com.morozov.quiz.controller.app.answer;

import android.annotation.SuppressLint;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.TextView;

import com.morozov.quiz.R;
import com.morozov.quiz.controller.models.QuestionModel;

import butterknife.BindView;
import butterknife.ButterKnife;

class AnswerViewHolder extends RecyclerView.ViewHolder {

    @BindView(R.id.tv_question_num)
    TextView tvQuestionNumber;

    @BindView(R.id.tv_question)
    TextView tvQuestion;

    @BindView(R.id.tv_correct_answer)
    TextView tvAnswer;

    @BindView(R.id.tv_explanation)
    TextView tvExplanation;

    AnswerViewHolder(@NonNull View itemView) {
        super(itemView);
        ButterKnife.bind(this, itemView);
    }

    @SuppressLint("StringFormatMatches")
    void populate(QuestionModel question, int position) {
        tvQuestionNumber.setText(String.format(itemView.getContext().getString(R.string.question_number), position));
        tvQuestion.setText(question.getQuestion());
        tvAnswer.setText(question.getCorrectAnswer());
    }
}
