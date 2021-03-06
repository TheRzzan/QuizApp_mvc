package com.morozov.quiz.controller.app.subsection_to_answer;

import androidx.lifecycle.Observer;
import android.content.Intent;
import android.os.Bundle;
import androidx.annotation.Nullable;
import androidx.appcompat.app.ActionBar;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import androidx.appcompat.widget.Toolbar;

import android.view.MenuItem;
import android.view.View;

import com.morozov.quiz.R;
import com.morozov.quiz.controller.ControllerActivity;
import com.morozov.quiz.controller.app.answer.AnswerActivity;
import com.morozov.quiz.controller.app.subsection.SubsectionAdapter;
import com.morozov.quiz.controller.models.SubsectionModel;
import com.morozov.quiz.utility.ActivityNavigation;
import com.morozov.quiz.utility.ActivityTitles;
import com.morozov.quiz.utility.ActivityUtility;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

public class SubsectionActivity extends ControllerActivity<SubsectionViewModel, SubsectionController> {

    @BindView(R.id.toolbar)
    Toolbar toolbar;

//    @BindView(R.id.searchView)
//    SimpleSearchView searchView;

    @BindView(R.id.rvSubsections)
    RecyclerView rvSubsections;

    private SubsectionAdapter adapter;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_subsection_to_answer);
        ButterKnife.bind(this);

        setSupportActionBar(toolbar);
        ActionBar actionBar = getSupportActionBar();
        if (actionBar != null) {
            actionBar.setTitle(ActivityTitles.getInstance(getApplicationContext()).getAirplaneName() + " " + getString(R.string.answers));
            actionBar.setDisplayHomeAsUpEnabled(true);
        }

        adapter = new SubsectionAdapter(getApplicationContext(), getController(), "0");
        rvSubsections.setAdapter(adapter);
        rvSubsections.setLayoutManager(new LinearLayoutManager(this));
    }

//    @Override
//    public boolean onCreateOptionsMenu(Menu menu) {
//        MenuInflater inflater = getMenuInflater();
//        inflater.inflate(R.menu.search_menu, menu);
//
//        MenuItem item = menu.findItem(R.id.action_search);
//        searchView.setMenuItem(item);
//
//        searchView.enableVoiceSearch(true);
//        searchView.setOnQueryTextListener(new SimpleSearchView.OnQueryTextListener() {
//            @Override
//            public boolean onQueryTextSubmit(String query) {
//
//                return false;
//            }
//
//            @Override
//            public boolean onQueryTextChange(String newText) {
//                adapter.getFilter().filter(newText);
//                return false;
//            }
//
//            @Override
//            public boolean onQueryTextCleared() {
//
//                return false;
//            }
//        });
//
//        return true;
//    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        if (item.getItemId() == android.R.id.home)
            this.onBackPressed();

        return super.onOptionsItemSelected(item);
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

        viewModel.subsectionName().observe(this, new Observer<String>() {
            @Override
            public void onChanged(@Nullable String s) {
                ActivityTitles.getInstance(getApplicationContext())
                        .setSubsectionName(s);
            }
        });

        viewModel.selectedSubsection().observe(this, new Observer<Integer>() {
            @Override
            public void onChanged(@Nullable Integer integer) {
                ActivityNavigation.getInstance(getApplicationContext())
                        .setSubsectionId(viewModel.subsections().getValue().get(integer - 1).getSubsectionId());

                ActivityUtility.invokeNewActivity(SubsectionActivity.this, AnswerActivity.class, true);
            }
        });
    }

    @Override
    protected void observeClicks(SubsectionController controller) {
        super.observeClicks(controller);
    }

    @Override
    protected SubsectionController createController(SubsectionViewModel viewModel) {
        SubsectionController SubsectionController = new SubsectionController(viewModel);
        SubsectionController.setContext(getApplicationContext());
        return SubsectionController;
    }

    @Override
    protected Class<SubsectionViewModel> viewModel() {
        return SubsectionViewModel.class;
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
//        if (searchView.onActivityResult(requestCode, resultCode, data)) {
//            return;
//        }

        super.onActivityResult(requestCode, resultCode, data);
    }

    @Override
    public void onBackPressed() {
//        if (searchView.onBackPressed()) {
//            return;
//        }

        ActivityUtility.invokeNewActivity(
                SubsectionActivity.this,
                com.morozov.quiz.controller.app.subsection.SubsectionActivity.class,
                true
        );
    }
}
