package com.morozov.quiz.controller.app.answer;

import android.annotation.SuppressLint;
import android.graphics.drawable.Drawable;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.morozov.quiz.R;
import com.morozov.quiz.controller.models.QuestionModel;
import com.morozov.quiz.utility.AppConstants;

import java.io.IOException;
import java.io.InputStream;

import butterknife.BindView;
import butterknife.ButterKnife;

class AnswerViewHolder extends RecyclerView.ViewHolder {

    @BindView(R.id.tv_question_num)
    TextView tvQuestionNumber;

    @BindView(R.id.tv_question)
    TextView tvQuestion;

    @BindView(R.id.ivQuestion)
    ImageView ivQuestion;

    @BindView(R.id.tv_correct_answer)
    TextView tvAnswer;

    @BindView(R.id.ivAnswer)
    ImageView ivAnswer;

    @BindView(R.id.tv_explanation)
    TextView tvExplanation;

    AnswerViewHolder(@NonNull View itemView) {
        super(itemView);
        ButterKnife.bind(this, itemView);
    }

    @SuppressLint("StringFormatMatches")
    void populate(QuestionModel question, int position) {
        if (question.isImageQuestion()) {
            ivQuestion.setVisibility(View.VISIBLE);

            InputStream inputStream = null;
            try{
                inputStream = ivQuestion.getContext().getAssets().open(AppConstants.IMAGE_DIR + question.getQuestionImage());
                Drawable d = Drawable.createFromStream(inputStream, null);
                ivQuestion.setImageDrawable(d);
                ivQuestion.setScaleType(ImageView.ScaleType.FIT_XY);
            }
            catch (IOException e){
                e.printStackTrace();
            }
            finally {
                try{
                    if(inputStream!=null)
                        inputStream.close();
                }
                catch (IOException ex){
                    ex.printStackTrace();
                }
            }
        } else {
            ivQuestion.setVisibility(View.GONE);
        }

        if (question.isImageAnswer()) {
            tvAnswer.setVisibility(View.GONE);
            ivAnswer.setVisibility(View.VISIBLE);

            ivAnswer.setVisibility(View.VISIBLE);

            InputStream inputStream = null;
            try{
                inputStream = ivAnswer.getContext().getAssets().open(AppConstants.IMAGE_DIR + question.getCorrectAnswer());
                Drawable d = Drawable.createFromStream(inputStream, null);
                ivAnswer.setImageDrawable(d);
                ivAnswer.setScaleType(ImageView.ScaleType.FIT_XY);
            }
            catch (IOException e){
                e.printStackTrace();
            }
            finally {
                try{
                    if(inputStream!=null)
                        inputStream.close();
                }
                catch (IOException ex){
                    ex.printStackTrace();
                }
            }
        } else {
            ivAnswer.setVisibility(View.GONE);
            tvAnswer.setVisibility(View.VISIBLE);
            tvAnswer.setText(question.getCorrectAnswer());
        }

        tvQuestionNumber.setText(String.format(itemView.getContext().getString(R.string.question_number), position));
        tvQuestion.setText(question.getQuestion());
    }
}
