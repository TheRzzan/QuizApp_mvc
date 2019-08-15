package com.morozov.quiz.controller.app.subsection;

import android.arch.lifecycle.Observer;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.ActionBar;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.Filter;
import android.widget.TextView;

import com.ferfalk.simplesearchview.SimpleSearchView;
import com.morozov.quiz.R;
import com.morozov.quiz.controller.ControllerActivity;
import com.morozov.quiz.controller.app.airplane.AirplaneActivity;
import com.morozov.quiz.controller.app.topic.TopicActivity;
import com.morozov.quiz.controller.models.SectionModel;
import com.morozov.quiz.utility.ActivityNavigation;
import com.morozov.quiz.utility.ActivityTitles;
import com.morozov.quiz.utility.ActivityUtility;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

public class SubsectionActivity extends ControllerActivity<SubsectionViewModel, SubsectionController> {

    @BindView(R.id.toolbar)
    Toolbar toolbar;

    @BindView(R.id.searchView)
    SimpleSearchView searchView;

    @BindView(R.id.rvSubsections)
    RecyclerView rvSubsections;

    @BindView(R.id.tvRecycler)
    TextView answer;

    private SectionAdapter adapter;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_subsection_new);
        ButterKnife.bind(this);

        setSupportActionBar(toolbar);
        ActionBar actionBar = getSupportActionBar();
        if (actionBar != null) {
            actionBar.setTitle(ActivityTitles.getInstance(getApplicationContext()).getAirplaneName());
            actionBar.setDisplayHomeAsUpEnabled(true);
        }

        if (!ActivityNavigation.getInstance(getApplicationContext()).getToTest())
            answer.setVisibility(View.GONE);

        adapter = new SectionAdapter(getApplicationContext(), getController());
        rvSubsections.setAdapter(adapter);
        rvSubsections.setLayoutManager(new LinearLayoutManager(this));
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.search_menu, menu);

        MenuItem item = menu.findItem(R.id.action_search);
        searchView.setMenuItem(item);

        searchView.enableVoiceSearch(true);
        searchView.setOnQueryTextListener(new SimpleSearchView.OnQueryTextListener() {
            @Override
            public boolean onQueryTextSubmit(String query) {

                return false;
            }

            @Override
            public boolean onQueryTextChange(String newText) {
                for (Filter filter : adapter.getFilters()) {
                    filter.filter(newText);
                }
                return false;
            }

            @Override
            public boolean onQueryTextCleared() {

                return false;
            }
        });

        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        if (item.getItemId() == android.R.id.home)
            this.onBackPressed();

        return super.onOptionsItemSelected(item);
    }

    @Override
    protected void observe(SubsectionViewModel viewModel) {
        super.observe(viewModel);

        viewModel.sections().observe(this, new Observer<List<SectionModel>>() {
            @Override
            public void onChanged(@Nullable List<SectionModel> sectionModels) {
                adapter.setData(sectionModels);
                rvSubsections.setVisibility(View.VISIBLE);
            }
        });

        viewModel.selectedSubsection().observe(this, new Observer<Integer>() {
            @Override
            public void onChanged(@Nullable Integer integer) {
                ActivityTitles.getInstance(getApplicationContext())
                        .setSubsectionName(viewModel.subsectionName().getValue());

                ActivityNavigation.getInstance(getApplicationContext())
                        .setSubsectionId(viewModel.selectedSubsection().getValue().toString());

                ActivityNavigation.getInstance(getApplicationContext())
                        .setSectionId(viewModel.selectedSection().getValue().toString());

                ActivityUtility.invokeNewActivity(SubsectionActivity.this, TopicActivity.class, true);
            }
        });
    }

    @Override
    protected void observeClicks(SubsectionController controller) {
        super.observeClicks(controller);

        answer.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                ActivityNavigation.getInstance(getApplicationContext())
                        .setToTest(false);

                ActivityUtility.invokeNewActivity(SubsectionActivity.this, com.morozov.quiz.controller.app.subsection.SubsectionActivity.class, true);
            }
        });
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
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        if (searchView.onActivityResult(requestCode, resultCode, data)) {
            return;
        }

        super.onActivityResult(requestCode, resultCode, data);
    }

    @Override
    public void onBackPressed() {
        if (searchView.onBackPressed()) {
            return;
        }

        if (ActivityNavigation.getInstance(getApplicationContext()).getToTest())
            ActivityUtility.invokeNewActivity(SubsectionActivity.this, AirplaneActivity.class, true);
        else {
            ActivityNavigation.getInstance(getApplicationContext())
                    .setToTest(true);

            ActivityUtility.invokeNewActivity(SubsectionActivity.this, com.morozov.quiz.controller.app.subsection.SubsectionActivity.class, true);
        }
    }
}
