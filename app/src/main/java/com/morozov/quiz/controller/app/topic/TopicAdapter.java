package com.morozov.quiz.controller.app.topic;

import android.content.Context;
import android.support.annotation.NonNull;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Filter;
import android.widget.Filterable;

import com.morozov.quiz.R;
import com.morozov.quiz.controller.models.TopicModel;
import com.morozov.quiz.controller.ui.ListAdapter;

import java.util.ArrayList;
import java.util.List;

public class TopicAdapter extends ListAdapter<TopicModel, TopicViewHolder> implements Filterable {
    private final LayoutInflater inflater;
    private final View.OnClickListener listener;

    private List<TopicModel> dataFull;

    TopicAdapter(Context context, View.OnClickListener listener) {
        inflater = LayoutInflater.from(context);
        this.listener = listener;
    }

    @NonNull
    @Override
    public TopicViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        return new TopicViewHolder(inflater.inflate(R.layout.item_text_view, null));
    }

    @Override
    public void onBindViewHolder(@NonNull TopicViewHolder holder, int i) {
        holder.populate(data().get(i));
        holder.setOnClick(listener, i);
    }

    @Override
    public void setData(List<TopicModel> data) {
        super.setData(data);

        dataFull = new ArrayList<>(data);
    }

    @Override
    public Filter getFilter() {
        return mFilter;
    }

    private Filter mFilter = new Filter() {
        @Override
        protected FilterResults performFiltering(CharSequence constraint) {
            List<TopicModel> filteredList = new ArrayList<>();

            if (constraint == null || constraint.length() == 0) {
                filteredList.addAll(dataFull);
            } else {
                String filterPattern = constraint.toString().toLowerCase().trim();

                for (TopicModel item : dataFull) {
                    if (item.getTopicName().toLowerCase().contains(filterPattern)) {
                        filteredList.add(item);
                    }
                }
            }

            FilterResults results = new FilterResults();
            results.values = filteredList;

            return results;
        }

        @Override
        protected void publishResults(CharSequence constraint, FilterResults results) {
            data().clear();
            data().addAll((List) results.values);
            notifyDataSetChanged();
        }
    };
}
