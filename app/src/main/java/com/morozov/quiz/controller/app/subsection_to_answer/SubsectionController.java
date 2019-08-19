package com.morozov.quiz.controller.app.subsection_to_answer;

import android.content.Context;

import com.morozov.quiz.controller.Controller;
import com.morozov.quiz.controller.interaction.SubsectionClickListener;
import com.morozov.quiz.controller.models.SectionModel;
import com.morozov.quiz.controller.models.SubsectionModel;
import com.morozov.quiz.utility.ActivityNavigation;
import com.morozov.quiz.utility.DataLoader;

import java.util.ArrayList;

public class SubsectionController extends Controller<SubsectionViewModel> implements SubsectionClickListener {

    private Context context;

    public SubsectionController(SubsectionViewModel viewModel) {
        super(viewModel);
    }

    void setContext(Context context) {
        this.context = context;
    }

    @Override
    protected void onStart() {
        initialSections();
    }

    private void initialSections() {
        ArrayList<SubsectionModel> subsections = new ArrayList<>();

        ArrayList<SectionModel> sections = DataLoader.getSections(
                context.getAssets(),
                ActivityNavigation.getInstance(context).getAirplaneId()
        );

        for (SectionModel section : sections) {
            subsections.addAll(DataLoader.getSubsections(
                    context.getAssets(),
                    section.getSectionId()
            ));
        }

        viewModel().subsections().setValue(subsections);
    }

    @Override
    public void onClick(int section, int subsection, String subsectionName) {
        viewModel().selectedSection().setValue(section);
        viewModel().subsectionName().setValue(subsectionName);
        viewModel().selectedSubsection().setValue(subsection);
    }
}
