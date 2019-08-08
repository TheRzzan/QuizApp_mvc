package com.morozov.quiz.controller.app.section_to_answer;

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

import com.ferfalk.simplesearchview.SimpleSearchView;
import com.morozov.quiz.R;
import com.morozov.quiz.controller.ControllerActivity;
import com.morozov.quiz.controller.app.section.SectionViewModel;
import com.morozov.quiz.controller.models.SectionModel;
import com.morozov.quiz.utility.ActivityTitles;
import com.morozov.quiz.utility.ActivityUtility;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

public class SectionActivity extends ControllerActivity<SectionViewModel, SectionController> {

    @BindView(R.id.toolbar)
    Toolbar toolbar;

    @BindView(R.id.searchView)
    SimpleSearchView searchView;

    @BindView(R.id.rvSections)
    RecyclerView rvSections;

    private SectionAdapter adapter;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_section_to_answer);
        ButterKnife.bind(this);

        setSupportActionBar(toolbar);
        ActionBar actionBar = getSupportActionBar();
        if (actionBar != null) {
            ActivityTitles.getInstance(getApplicationContext()).setSectionName(null);
            actionBar.setTitle(ActivityTitles.getInstance(getApplicationContext()).getSectionName());
            actionBar.setDisplayHomeAsUpEnabled(true);
        }

        adapter = new SectionAdapter(getApplicationContext(), getController());
        rvSections.setAdapter(adapter);
        rvSections.setLayoutManager(new LinearLayoutManager(this));
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        if (item.getItemId() == android.R.id.home)
            this.onBackPressed();

        return super.onOptionsItemSelected(item);
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
                adapter.getFilter().filter(newText);
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
                ActivityTitles.getInstance(getApplicationContext())
                        .setSectionName(getViewModel().sections().getValue().get(integer).getSectionName());

                ActivityUtility.invokeSubsectionActivity(SectionActivity.this, true,
                        getViewModel().sections().getValue().get(integer).getSectionId(),
                        false);
            }
        });
    }

    @Override
    protected void observeClicks(SectionController controller) {
        super.observeClicks(controller);



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

        ActivityUtility.invokeNewActivity(SectionActivity.this, com.morozov.quiz.controller.app.section.SectionActivity.class, true);
    }
}
