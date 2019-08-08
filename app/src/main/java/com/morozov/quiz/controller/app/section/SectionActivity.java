package com.morozov.quiz.controller.app.section;

import android.arch.lifecycle.Observer;
import android.os.Bundle;
import android.os.Handler;
import android.support.annotation.Nullable;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import com.morozov.quiz.R;
import com.morozov.quiz.controller.ControllerActivity;
import com.morozov.quiz.controller.models.SectionModel;
import com.morozov.quiz.utility.ActivityUtility;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

public class SectionActivity extends ControllerActivity<SectionViewModel, SectionController> {
    @BindView(R.id.btn_answers)
    Button btnAnswers;

    @BindView(R.id.rvSections)
    RecyclerView rvSections;

    private SectionAdapter adapter;

    private Boolean exit = false;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_section);
        ButterKnife.bind(this);

        adapter = new SectionAdapter(getApplicationContext(), getController());
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

        viewModel.selectedSection().observe(this, new Observer<Integer>() {
            @Override
            public void onChanged(@Nullable Integer integer) {
                ActivityUtility.invokeSubsectionActivity(SectionActivity.this, true,
                        getViewModel().sections().getValue().get(integer).getSectionId(),
                        true);
            }
        });
    }

    @Override
    protected void observeClicks(SectionController controller) {
        super.observeClicks(controller);

        btnAnswers.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                ActivityUtility.invokeNewActivity(SectionActivity.this, com.morozov.quiz.controller.app.section_to_answer.SectionActivity.class, true);
            }
        });
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

    @Override
    public void onBackPressed() {
        if (exit) {
            finish();
        } else {
            Toast.makeText(this, getString(R.string.press_back_again), Toast.LENGTH_SHORT).show();
            exit = true;
            new Handler().postDelayed(new Runnable() {
                @Override
                public void run() {
                    exit = false;
                }
            }, 3 * 1000);
        }
    }
}
