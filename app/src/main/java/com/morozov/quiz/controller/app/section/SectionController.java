package com.morozov.quiz.controller.app.section;

import android.content.Context;
import android.view.View;

import com.morozov.quiz.controller.Controller;
import com.morozov.quiz.utility.ActivityNavigation;
import com.morozov.quiz.utility.DataLoader;

public class SectionController extends Controller<SectionViewModel> implements View.OnClickListener {

    private Context context;

    SectionController(SectionViewModel viewModel) {
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

//    private void openAnswers() {
//        Toast.makeText(context, "Answers", Toast.LENGTH_SHORT).show();
//    }

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
