package com.morozov.quiz.controller.app.quiz;

import android.content.Context;
import android.graphics.Color;
import android.support.annotation.NonNull;
import android.support.v4.app.FragmentManager;
import android.util.Log;
import android.util.Pair;
import android.view.LayoutInflater;
import android.view.ViewGroup;

import com.morozov.quiz.R;
import com.morozov.quiz.controller.interaction.AnswerClickListener;
import com.morozov.quiz.controller.interaction.HighlightClickListener;
import com.morozov.quiz.controller.models.MessageFromControllerModel;
import com.morozov.quiz.controller.ui.ListAdapter;

import java.util.List;

public class QuizAdapter extends ListAdapter<Pair<String, String>, QuizViewHolder> implements HighlightClickListener {

    private static String UNSELECTED_COL = "#FFFFFF";

    private final LayoutInflater inflater;
    private final AnswerClickListener listener;

    private MessageFromControllerModel message = null;
    private int row_index = -1;

    private boolean isImageAnswer = false;

    private final FragmentManager fragmentManager;

    QuizAdapter(Context context, AnswerClickListener listener, FragmentManager fragmentManager) {
        inflater = LayoutInflater.from(context);
        this.listener = listener;
        this.fragmentManager = fragmentManager;
    }

    @NonNull
    @Override
    public QuizViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        return new QuizViewHolder(inflater.inflate(R.layout.item_text_view_check, viewGroup, false));
    }

    @Override
    public void onBindViewHolder(@NonNull QuizViewHolder holder, int i) {
        if (row_index == -1 && message == null) {
            showWithoutClick(holder, i);
        } else if (row_index >= 0 && message == null) {
            showFirstClick(holder, i);
        } else if (row_index >= 0 && message != null) {
            showSecondClick(holder, i);
        }
    }

    private void showWithoutClick(QuizViewHolder holder, int i) {
        holder.populate(fragmentManager, data().get(i).second, data().get(i).first);
        holder.setOnClick(this, i);

        holder.itemView.setBackgroundColor(Color.parseColor(UNSELECTED_COL));
    }

    private void showFirstClick(QuizViewHolder holder, int i) {
        showWithoutClick(holder, i);

        if (row_index == i) {
            holder.itemView.setBackgroundResource(R.drawable.rectangle_grey_sharp);
        }
    }

    private void showSecondClick(QuizViewHolder holder, int i) {
        holder.populate(fragmentManager, data().get(i).second, data().get(i).first);
        holder.setOnClick(null, i);

        String answerI;
        if (isImageAnswer)
            answerI = data().get(i).second;
        else
            answerI = data().get(i).first;

        if (row_index == i) {
            if (message.isCorrectAnswer()) {
                holder.itemView.setBackgroundResource(R.drawable.rectangle_green_sharp);
                holder.setCheck(true);
            } else {
                holder.itemView.setBackgroundResource(R.drawable.rectangle_red_sharp);
                holder.setCheck(false);
            }
        } else if (message.getCorrectAnswer().equals(answerI)) {
            holder.itemView.setBackgroundResource(R.drawable.rectangle_green_sharp);
            holder.setCheck(true);
        } else {
            holder.itemView.setBackgroundColor(Color.parseColor(UNSELECTED_COL));
        }
    }

    @Override
    public void onItemClicked(int position) {
        row_index = position;
        String answerUser;
        if (isImageAnswer)
            answerUser = data().get(position).second;
        else
            answerUser = data().get(position).first;
        message = listener.onAnswerClicked(position, answerUser);
        notifyDataSetChanged();
    }

    @Override
    public void setData(List<Pair<String, String>> data) {
        super.setData(data);

        row_index = -1;
        message = null;

        notifyDataSetChanged();
    }

    void setImageAnswer(boolean imageAnswer) {
        isImageAnswer = imageAnswer;
    }
}
