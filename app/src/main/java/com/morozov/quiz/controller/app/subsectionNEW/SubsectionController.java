package com.morozov.quiz.controller.app.subsectionNEW;

import android.content.Context;

import com.morozov.quiz.controller.Controller;
import com.morozov.quiz.controller.interaction.SubsectionClickListener;
import com.morozov.quiz.utility.ActivityNavigation;
import com.morozov.quiz.utility.DataLoader;

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
        viewModel().sections()
                .setValue(DataLoader.getSections(
                        context.getAssets(),
                        ActivityNavigation.getInstance(context).getAirplaneId()
                ));
    }

    @Override
    public void onClick(int section, int subsection, String subsectionName) {
        viewModel().selectedSection().setValue(section);
        viewModel().subsectionName().setValue(subsectionName);
        viewModel().selectedSubsection().setValue(subsection);
    }
}
