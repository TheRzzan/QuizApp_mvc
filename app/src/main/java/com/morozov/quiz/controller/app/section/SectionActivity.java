package com.morozov.quiz.controller.app.section;

import android.arch.lifecycle.Observer;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.Button;

import com.morozov.quiz.R;
import com.morozov.quiz.controller.ControllerActivity;
import com.morozov.quiz.controller.models.SectionModel;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

public class SectionActivity extends ControllerActivity<SectionViewModel, SectionController> {
    @BindView(R.id.btn_answers)
    Button btnAnswers;

    @BindView(R.id.rvSections)
    RecyclerView rvSections;

    private SectionAdapter adapter;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_section);
        ButterKnife.bind(this);

        adapter = new SectionAdapter(getApplicationContext(), controller());
        rvSections.setAdapter(adapter);
        rvSections.setLayoutManager(new LinearLayoutManager(this));
    }

    @Override
    protected void observe(SectionViewModel viewModel) {
        super.observe(viewModel);

        viewModel.sections().observe(this, new Observer<List<SectionModel>>() {
            @Override
            public void onChanged(@Nullable List<SectionModel> sectionModels) {
                adapter.setData(sectionModels);
                rvSections.setVisibility(View.VISIBLE);
            }
        });
    }

    @Override
    protected void observeClicks(SectionController controller) {
        super.observeClicks(controller);

        btnAnswers.setOnClickListener(controller);
    }

    @Override
    protected SectionController createController(SectionViewModel viewModel) {
        SectionController sectionController = new SectionController(viewModel);
        sectionController.setContext(getApplicationContext());
        return sectionController;
    }

    @Override
    protected Class<SectionViewModel> viewModel() {
        return SectionViewModel.class;
    }
}
