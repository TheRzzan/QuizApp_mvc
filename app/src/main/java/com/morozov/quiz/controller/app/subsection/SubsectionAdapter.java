package com.morozov.quiz.controller.app.subsection;

import android.content.Context;
import android.support.annotation.NonNull;
import android.view.LayoutInflater;
import android.view.ViewGroup;
import android.widget.Filter;
import android.widget.Filterable;

import com.morozov.quiz.R;
import com.morozov.quiz.controller.interaction.SubsectionClickListener;
import com.morozov.quiz.controller.models.SubsectionModel;
import com.morozov.quiz.controller.ui.ListAdapter;

import java.util.ArrayList;
import java.util.List;

public class SubsectionAdapter extends ListAdapter<SubsectionModel, SubsectionViewHolder> implements Filterable {

    private final LayoutInflater inflater;
    private final SubsectionClickListener listener;

    private final int sectionId;

    private List<SubsectionModel> dataFull;

    public SubsectionAdapter(Context context, SubsectionClickListener listener, String sectionIdStr) {
        inflater = LayoutInflater.from(context);
        this.listener = listener;
        this.sectionId = Integer.parseInt(sectionIdStr);
    }

    @NonNull
    @Override
    public SubsectionViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        return new SubsectionViewHolder(inflater.inflate(R.layout.item_text_view, viewGroup, false));
    }

    @Override
    public void onBindViewHolder(@NonNull SubsectionViewHolder holder, int i) {
        holder.populate(data().get(i));
        holder.setOnClick(listener, sectionId, Integer.parseInt(data().get(i).getSubsectionId()), data().get(i).getSubsectionName());
    }

    @Override
    public void setData(List<SubsectionModel> data) {
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
            List<SubsectionModel> filteredList = new ArrayList<>();

            if (constraint == null || constraint.length() == 0) {
                filteredList.addAll(dataFull);
            } else {
                String filterPattern = constraint.toString().toLowerCase().trim();

                for (SubsectionModel item : dataFull) {
                    if (item.getSubsectionName().toLowerCase().contains(filterPattern)) {
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
