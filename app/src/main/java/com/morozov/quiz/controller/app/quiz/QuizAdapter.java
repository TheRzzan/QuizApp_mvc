package com.morozov.quiz.controller.app.quiz;

import android.content.Context;
import android.graphics.Color;
import android.support.annotation.NonNull;
import android.view.LayoutInflater;
import android.view.ViewGroup;

import com.morozov.quiz.R;
import com.morozov.quiz.controller.interaction.AnswerClickListener;
import com.morozov.quiz.controller.interaction.HighlightClickListener;
import com.morozov.quiz.controller.models.MessageFromControllerModel;
import com.morozov.quiz.controller.ui.ListAdapter;

import java.util.List;

public class QuizAdapter extends ListAdapter<String, QuizViewHolder> implements HighlightClickListener {

    private static String UNSELECTED_COL = "#FFFFFF";

    private final LayoutInflater inflater;
    private final AnswerClickListener listener;

    private MessageFromControllerModel message = null;
    private int row_index = -1;

    private boolean isImageAnswer = false;

    QuizAdapter(Context context, AnswerClickListener listener) {
        inflater = LayoutInflater.from(context);
        this.listener = listener;
    }

    @NonNull
    @Override
    public QuizViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        return new QuizViewHolder(inflater.inflate(R.layout.item_text_view_check, null));
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
        holder.populate(data().get(i), isImageAnswer);
        holder.setOnClick(this, i);

        holder.itemView.setBackgroundColor(Color.parseColor(UNSELECTED_COL));
    }

    private void showFirstClick(QuizViewHolder holder, int i) {
        if (row_index == i) {
            holder.itemView.setBackgroundResource(R.drawable.rectangle_grey_sharp);
        } else {
            holder.itemView.setBackgroundColor(Color.parseColor(UNSELECTED_COL));
        }
    }

    private void showSecondClick(QuizViewHolder holder, int i) {
        holder.setOnClick(null, i);

        if (row_index == i) {
            if (message.isCorrectAnswer()) {
                holder.itemView.setBackgroundResource(R.drawable.rectangle_green_sharp);
                holder.setCheck(true, isImageAnswer);
            } else {
                holder.itemView.setBackgroundResource(R.drawable.rectangle_red_sharp);
                holder.setCheck(false, isImageAnswer);
            }
        } else if (message.getCorrectAnswer().equals(data().get(i))) {
            holder.itemView.setBackgroundResource(R.drawable.rectangle_green_sharp);
            holder.setCheck(true, isImageAnswer);
        } else {
            holder.itemView.setBackgroundColor(Color.parseColor(UNSELECTED_COL));
        }
    }

    @Override
    public void onItemClicked(int position) {
        row_index = position;
        message = listener.onAnswerClicked(position, data().get(position));
        notifyDataSetChanged();
    }

    @Override
    public void setData(List<String> data) {
        super.setData(data);

        row_index = -1;
        message = null;

        notifyDataSetChanged();
    }

    void setImageAnswer(boolean imageAnswer) {
        isImageAnswer = imageAnswer;
    }
}
