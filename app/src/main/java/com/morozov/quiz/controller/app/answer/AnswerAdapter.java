package com.morozov.quiz.controller.app.answer;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v4.app.FragmentManager;
import android.view.LayoutInflater;
import android.view.ViewGroup;
import android.widget.Filter;
import android.widget.Filterable;

import com.morozov.quiz.R;
import com.morozov.quiz.controller.models.QuestionModel;
import com.morozov.quiz.controller.models.SubsectionModel;
import com.morozov.quiz.controller.ui.ListAdapter;

import java.util.ArrayList;
import java.util.List;

public class AnswerAdapter extends ListAdapter<QuestionModel, AnswerViewHolder> implements Filterable {
    private final LayoutInflater inflater;
    private final FragmentManager fragmentManager;

    private List<QuestionModel> dataFull;

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

    @Override
    public void setData(List<QuestionModel> data) {
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
            List<QuestionModel> filteredList = new ArrayList<>();

            if (constraint == null || constraint.length() == 0) {
                filteredList.addAll(dataFull);
            } else {
                String filterPattern = constraint.toString().toLowerCase().trim();

                for (QuestionModel item : dataFull) {
                    if (item.getQuestion().toLowerCase().contains(filterPattern)) {
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
