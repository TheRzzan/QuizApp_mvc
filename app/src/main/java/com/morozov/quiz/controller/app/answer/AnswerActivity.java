package com.morozov.quiz.controller.app.answer;

import androidx.lifecycle.Observer;
import android.os.Bundle;
import androidx.annotation.Nullable;
import androidx.appcompat.app.ActionBar;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import androidx.appcompat.widget.Toolbar;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.WindowManager;
import android.widget.Filter;

import com.ferfalk.simplesearchview.SimpleSearchView;
import com.morozov.quiz.R;
import com.morozov.quiz.controller.ControllerActivity;
import com.morozov.quiz.controller.app.subsection_to_answer.SubsectionActivity;
import com.morozov.quiz.controller.models.TopicModel;
import com.morozov.quiz.utility.ActivityTitles;
import com.morozov.quiz.utility.ActivityUtility;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

public class AnswerActivity extends ControllerActivity<AnswerViewModel, AnswerController> {

    @BindView(R.id.toolbar)
    Toolbar toolbar;

    @BindView(R.id.searchView)
    SimpleSearchView searchView;

    @BindView(R.id.rvTopics)
    RecyclerView rvTopics;

    private TopicAdapter adapter;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_answers);
        ButterKnife.bind(this);

        setSupportActionBar(toolbar);
        ActionBar actionBar = getSupportActionBar();
        if (actionBar != null) {
            actionBar.setTitle(ActivityTitles.getInstance(getApplicationContext()).getSubsectionName());
            actionBar.setDisplayHomeAsUpEnabled(true);
        }

        adapter = new TopicAdapter(AnswerActivity.this, getSupportFragmentManager());
        rvTopics.setAdapter(adapter);
        rvTopics.setLayoutManager(new LinearLayoutManager(getApplicationContext()));

        getWindow().setSoftInputMode(WindowManager.LayoutParams.SOFT_INPUT_STATE_HIDDEN);
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
    protected void observe(AnswerViewModel viewModel) {
        super.observe(viewModel);

        viewModel.topics().observe(this, new Observer<List<TopicModel>>() {
            @Override
            public void onChanged(@Nullable List<TopicModel> topicModels) {
                adapter.setData(topicModels);
                rvTopics.setVisibility(View.VISIBLE);
            }
        });
    }

    @Override
    protected void observeClicks(AnswerController controller) {
        super.observeClicks(controller);


    }

    @Override
    protected AnswerController createController(AnswerViewModel viewModel) {
        AnswerController answerController = new AnswerController(viewModel);
        answerController.setContext(getApplicationContext());
        return answerController;
    }

    @Override
    protected Class<AnswerViewModel> viewModel() {
        return AnswerViewModel.class;
    }

    @Override
    public void onBackPressed() {
        if (searchView.onBackPressed()) {
            return;
        }

        ActivityUtility.invokeNewActivity(AnswerActivity.this, SubsectionActivity.class, true);
    }
}
