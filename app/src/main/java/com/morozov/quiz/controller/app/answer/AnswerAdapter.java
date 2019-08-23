package com.morozov.quiz.controller.app.answer;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v4.app.FragmentManager;
import android.view.LayoutInflater;
import android.view.ViewGroup;

import com.morozov.quiz.R;
import com.morozov.quiz.controller.models.QuestionModel;
import com.morozov.quiz.controller.ui.ListAdapter;

public class AnswerAdapter extends ListAdapter<QuestionModel, AnswerViewHolder> {
    private final LayoutInflater inflater;
    private final FragmentManager fragmentManager;

    AnswerAdapter(Context context, FragmentManager fragmentManager) {
        this.inflater = LayoutInflater.from(context);
        this.fragmentManager = fragmentManager;
    }

    @NonNull
    @Override
    public AnswerViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        return new AnswerViewHolder(inflater.inflate(R.layout.item_answer, null));
    }

    @Override
    public void onBindViewHolder(@NonNull AnswerViewHolder holder, int i) {
        holder.populate(fragmentManager, data().get(i), i + 1);
    }
}
