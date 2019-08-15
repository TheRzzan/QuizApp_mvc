package com.morozov.quiz.controller.app.airplane;

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
import com.morozov.quiz.controller.models.AirplaneModel;
import com.morozov.quiz.utility.ActivityNavigation;
import com.morozov.quiz.utility.ActivityTitles;
import com.morozov.quiz.utility.ActivityUtility;
import com.morozov.quiz.utility.DataLoader;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

public class AirplaneActivity extends ControllerActivity<AirplaneViewModel, AirplaneController> {

    @BindView(R.id.btn_answers)
    Button btnAnswers;

    @BindView(R.id.rvSections)
    RecyclerView rvSections;

    private AirplaneAdapter adapter;

    private Boolean exit = false;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_section);
        ButterKnife.bind(this);

        btnAnswers.setVisibility(View.GONE);

        adapter = new AirplaneAdapter(getApplicationContext(), getController());
        rvSections.setAdapter(adapter);
        rvSections.setLayoutManager(new LinearLayoutManager(this));
    }

    @Override
    protected void observe(AirplaneViewModel viewModel) {
        super.observe(viewModel);

        viewModel.airplanes().observe(this, new Observer<List<AirplaneModel>>() {
            @Override
            public void onChanged(@Nullable List<AirplaneModel> airplaneModels) {
                adapter.setData(airplaneModels);
                rvSections.setVisibility(View.VISIBLE);
            }
        });

        viewModel.selectedAirplane().observe(this, new Observer<Integer>() {
            @Override
            public void onChanged(@Nullable Integer integer) {
                if (DataLoader.getSections(getAssets(), getViewModel().airplanes().getValue().get(integer).getAirplaneId()).isEmpty())
                    return;

                ActivityTitles.getInstance(getApplicationContext())
                        .setAirplaneName(getViewModel().airplanes().getValue().get(integer).getAirplaneName());

                ActivityNavigation.getInstance(getApplicationContext())
                        .setAirplaneId(getViewModel().airplanes().getValue().get(integer).getAirplaneId());

                ActivityNavigation.getInstance(getApplicationContext())
                        .setToTest(true);

                ActivityUtility.invokeNewActivity(AirplaneActivity.this, com.morozov.quiz.controller.app.subsection.SubsectionActivity.class, true);
            }
        });
    }

    @Override
    protected void observeClicks(AirplaneController controller) {
        super.observeClicks(controller);
    }

    @Override
    protected AirplaneController createController(AirplaneViewModel viewModel) {
        AirplaneController airplaneController = new AirplaneController(viewModel);
        airplaneController.setContext(getApplicationContext());
        return airplaneController;
    }

    @Override
    protected Class<AirplaneViewModel> viewModel() {
        return AirplaneViewModel.class;
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
