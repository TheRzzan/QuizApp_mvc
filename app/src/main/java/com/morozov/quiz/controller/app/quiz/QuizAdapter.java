package com.morozov.quiz.controller.app.quiz;

import android.content.Context;
import android.graphics.Color;
import android.support.annotation.NonNull;
import android.view.LayoutInflater;
import android.view.ViewGroup;

import com.morozov.quiz.R;
import com.morozov.quiz.controller.interaction.AnswerClickListener;
import com.morozov.quiz.controller.interaction.HighlightClickListener;
import com.morozov.quiz.controller.ui.ListAdapter;

import java.util.List;

public class QuizAdapter extends ListAdapter<String, QuizViewHolder> implements HighlightClickListener {
    private final LayoutInflater inflater;
    private final AnswerClickListener listener;

    private int row_index = -1;

    QuizAdapter(Context context, AnswerClickListener listener) {
        inflater = LayoutInflater.from(context);
        this.listener = listener;
    }

    @NonNull
    @Override
    public QuizViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        return new QuizViewHolder(inflater.inflate(R.layout.item_answer_recycler, null));
    }

    @Override
    public void onBindViewHolder(@NonNull QuizViewHolder holder, int i) {
        holder.populate(data().get(i));
        holder.setOnClick(this, i);

        if (row_index == i) {
            holder.itemView.setBackgroundColor(Color.parseColor("#000000"));
            holder.tvAnswer.setTextColor(Color.parseColor("#FFFFFF"));
        } else {
            holder.itemView.setBackgroundColor(Color.parseColor("#FFFFFF"));
            holder.tvAnswer.setTextColor(Color.parseColor("#000000"));
        }
    }

    @Override
    public void onItemClicked(int position, String answer) {
        row_index = position;
        notifyDataSetChanged();
        listener.onAnswerClicked(position, answer);
    }

    @Override
    public void setData(List<String> data) {
        super.setData(data);
        row_index = -1;
    }
}
