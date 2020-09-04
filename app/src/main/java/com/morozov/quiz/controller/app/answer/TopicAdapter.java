package com.morozov.quiz.controller.app.answer;

import android.content.Context;
import androidx.annotation.NonNull;
import androidx.fragment.app.FragmentManager;
import android.view.LayoutInflater;
import android.view.ViewGroup;
import android.widget.Filter;

import com.morozov.quiz.R;
import com.morozov.quiz.controller.models.TopicModel;
import com.morozov.quiz.controller.ui.ListAdapter;

import java.util.ArrayList;
import java.util.List;

public class TopicAdapter extends ListAdapter<TopicModel, TopicViewHolder> {
    private final LayoutInflater inflater;
    private final FragmentManager fragmentManager;

    private List<Filter> filters;

    TopicAdapter(Context activity, FragmentManager fragmentManager) {
        this.inflater = LayoutInflater.from(activity);
        this.fragmentManager = fragmentManager;
        filters = new ArrayList<>();
    }

    @NonNull
    @Override
    public TopicViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        return new TopicViewHolder(inflater.inflate(R.layout.item_answer_topic, null));
    }

    @Override
    public void onBindViewHolder(@NonNull TopicViewHolder holder, int i) {
        filters.add(holder.populate(fragmentManager, data().get(i), i + 1));
    }

    public List<Filter> getFilters() {
        return filters;
    }
}
