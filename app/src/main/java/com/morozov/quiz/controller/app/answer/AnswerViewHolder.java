package com.morozov.quiz.controller.app.answer;

import android.annotation.SuppressLint;
import android.graphics.drawable.Drawable;
import androidx.annotation.NonNull;
import androidx.fragment.app.FragmentManager;
import androidx.recyclerview.widget.RecyclerView;
import android.text.Html;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.morozov.quiz.R;
import com.morozov.quiz.controller.app.quiz.QuizActivity;
import com.morozov.quiz.controller.models.QuestionModel;
import com.morozov.quiz.controller.ui.ImageDialog;
import com.morozov.quiz.utility.AppConstants;

import java.io.IOException;
import java.io.InputStream;

import butterknife.BindView;
import butterknife.ButterKnife;

class AnswerViewHolder extends RecyclerView.ViewHolder {

    @BindView(R.id.tv_question)
    TextView tvQuestion;

    @BindView(R.id.ivQuestion)
    ImageView ivQuestion;

    @BindView(R.id.tv_correct_answer)
    TextView tvAnswer;

    @BindView(R.id.ivAnswer)
    ImageView ivAnswer;

    AnswerViewHolder(@NonNull View itemView) {
        super(itemView);
        ButterKnife.bind(this, itemView);
    }

    @SuppressLint("StringFormatMatches")
    void populate(FragmentManager fragmentManager, QuestionModel question, int position) {
        if (question.isImageQuestion()) {
            ivQuestion.setVisibility(View.VISIBLE);

            ivQuestion.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    ImageDialog imageDialog = new ImageDialog();
                    imageDialog.setImage(AppConstants.IMAGE_DIR + question.getQuestionImage());
                    imageDialog.show(fragmentManager, QuizActivity.class.getSimpleName());
                }
            });

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

            ivAnswer.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    ImageDialog imageDialog = new ImageDialog();
                    imageDialog.setImage(AppConstants.IMAGE_DIR + question.getQuestionImage());
                    imageDialog.show(fragmentManager, QuizActivity.class.getSimpleName());
                }
            });

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
            String text = question.getCorrectAnswer().replace("\n", "<br>");
            tvAnswer.setText(Html.fromHtml(text));
        }
        String text2 = question.getQuestion().replace("\n", "<br>");
        tvQuestion.setText(Html.fromHtml(text2));
    }
}
