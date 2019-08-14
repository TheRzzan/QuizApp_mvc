package com.morozov.quiz.controller.app.subsection;

import android.content.Context;
import android.view.View;

import com.morozov.quiz.controller.Controller;
import com.morozov.quiz.utility.ActivityNavigation;
import com.morozov.quiz.utility.DataLoader;

public class SubsectionController extends Controller<SubsectionViewModel> implements View.OnClickListener {

    private Context context;

    SubsectionController(SubsectionViewModel viewModel) {
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
        viewModel().subsections()
                .setValue(DataLoader.getSubsections(
                        context.getAssets(),
                        ActivityNavigation.getInstance(context).getSectionId()
                ));
    }

    private void openTopic(Integer position) {
        viewModel().selectedSubsection().setValue(position);
    }

    @Override
    public void onClick(View v) {
        openTopic((Integer) v.getTag());
    }
}
