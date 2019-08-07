package com.morozov.quiz.controller.app.section_to_answer;

import android.content.Context;
import android.support.annotation.NonNull;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Filter;
import android.widget.Filterable;

import com.morozov.quiz.R;
import com.morozov.quiz.controller.models.SectionModel;
import com.morozov.quiz.controller.ui.ListAdapter;

import java.util.ArrayList;
import java.util.List;

class SectionAdapter extends ListAdapter<SectionModel, SectionViewHolder> implements Filterable {
    private final LayoutInflater inflater;
    private final View.OnClickListener listener;

    private List<SectionModel> dataFull;

    SectionAdapter(Context context, View.OnClickListener listener) {
        inflater = LayoutInflater.from(context);
        this.listener = listener;
    }

    @NonNull
    @Override
    public SectionViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        return new SectionViewHolder(inflater.inflate(R.layout.item_text_view_recycler, null));
    }

    @Override
    public void onBindViewHolder(@NonNull SectionViewHolder holder, int i) {
        holder.populate(data().get(i));
        holder.setOnClick(listener, i);
    }

    @Override
    public void setData(List<SectionModel> data) {
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
            List<SectionModel> filteredList = new ArrayList<>();

            if (constraint == null || constraint.length() == 0) {
                filteredList.addAll(dataFull);
            } else {
                String filterPattern = constraint.toString().toLowerCase().trim();

                for (SectionModel item : dataFull) {
                    if (item.getSectionName().toLowerCase().contains(filterPattern)) {
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
