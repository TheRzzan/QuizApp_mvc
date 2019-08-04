package com.morozov.quiz.controller.app.quiz;

import android.content.Context;
import android.support.annotation.NonNull;
import android.view.LayoutInflater;
import android.view.ViewGroup;

import com.morozov.quiz.R;
import com.morozov.quiz.controller.interaction.AnswerClickListener;
import com.morozov.quiz.controller.models.QuestionModel;
import com.morozov.quiz.controller.ui.ListAdapter;

public class QuizAdapter extends ListAdapter<String, QuizViewHolder> {
    private final LayoutInflater inflater;
    private final AnswerClickListener listener;

    public QuizAdapter(Context context, AnswerClickListener listener) {
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
        holder.setOnClick(listener, i);
    }
}
