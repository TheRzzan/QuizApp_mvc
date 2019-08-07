package com.morozov.quiz.controller.app.section_to_answer;

import android.content.Context;
import android.view.View;

import com.morozov.quiz.controller.Controller;
import com.morozov.quiz.controller.app.section.SectionViewModel;
import com.morozov.quiz.utility.DataLoader;

public class SectionController extends Controller<SectionViewModel> implements View.OnClickListener {

    private Context context;

    public SectionController(SectionViewModel viewModel) {
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
        viewModel().sections().setValue(DataLoader.getSections(context.getAssets()));
    }

    private void openSubsection(Integer position) {
        viewModel().selectedSection().setValue(position);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
//            case R.id.btn_answers:
//                openAnswers();
//                break;
            default:
                openSubsection((Integer) v.getTag());
                break;
        }
    }
}
