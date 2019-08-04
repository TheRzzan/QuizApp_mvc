package com.morozov.quiz.controller.app.subsection;

import android.arch.lifecycle.Observer;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.Toast;

import com.morozov.quiz.MainActivity;
import com.morozov.quiz.R;
import com.morozov.quiz.controller.ControllerActivity;
import com.morozov.quiz.controller.app.section.SectionActivity;
import com.morozov.quiz.controller.app.topic.TopicActivity;
import com.morozov.quiz.controller.models.SubsectionModel;
import com.morozov.quiz.utility.ActivityUtility;
import com.morozov.quiz.utility.AppConstants;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

public class SubsectionActivity extends ControllerActivity<SubsectionViewModel, SubsectionController> {

    @BindView(R.id.toolbar)
    Toolbar toolbar;

    @BindView(R.id.rvSubsections)
    RecyclerView rvSubsections;

    private SubsectionAdapter adapter;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_subsection);
        ButterKnife.bind(this);

        setSupportActionBar(toolbar);

        getViewModel().sectionId().setValue(getIntent().getStringExtra(AppConstants.JSON_KEY_SECTION_ID));

        adapter = new SubsectionAdapter(getApplicationContext(), getController());
        rvSubsections.setAdapter(adapter);
        rvSubsections.setLayoutManager(new LinearLayoutManager(this));
    }

    @Override
    protected void observe(SubsectionViewModel viewModel) {
        super.observe(viewModel);

        viewModel.subsections().observe(this, new Observer<List<SubsectionModel>>() {
            @Override
            public void onChanged(@Nullable List<SubsectionModel> subsectionModels) {
                adapter.setData(subsectionModels);
                rvSubsections.setVisibility(View.VISIBLE);
            }
        });

        viewModel.selectedSubsection().observe(this, new Observer<Integer>() {
            @Override
            public void onChanged(@Nullable Integer integer) {
                ActivityUtility.invokeTopicActivity(SubsectionActivity.this, true,
                        getViewModel().subsections().getValue().get(integer).getSubsectionId(),
                        getViewModel().sectionId().getValue());
            }
        });
    }

    @Override
    protected void observeClicks(SubsectionController controller) {
        super.observeClicks(controller);


    }

    @Override
    protected SubsectionController createController(SubsectionViewModel viewModel) {
        SubsectionController subsectionController = new SubsectionController(viewModel);
        subsectionController.setContext(getApplicationContext());
        return subsectionController;
    }

    @Override
    protected Class<SubsectionViewModel> viewModel() {
        return SubsectionViewModel.class;
    }

    @Override
    public void onBackPressed() {
        ActivityUtility.invokeNewActivity(SubsectionActivity.this, SectionActivity.class, true);
    }
}
