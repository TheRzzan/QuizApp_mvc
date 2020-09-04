package com.morozov.quiz.controller.app.topic;

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
import android.view.WindowManager;

import com.morozov.quiz.R;
import com.morozov.quiz.controller.ControllerActivity;
import com.morozov.quiz.controller.app.quiz.QuizActivity;
import com.morozov.quiz.controller.interaction.DialogClickListener;
import com.morozov.quiz.controller.models.TopicModel;
import com.morozov.quiz.controller.ui.CustomDialog;
import com.morozov.quiz.utility.ActivityNavigation;
import com.morozov.quiz.utility.ActivityTitles;
import com.morozov.quiz.utility.ActivityUtility;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

public class TopicActivity extends ControllerActivity<TopicViewModel, TopicController> implements DialogClickListener {

    @BindView(R.id.toolbar)
    Toolbar toolbar;

//    @BindView(R.id.searchView)
//    SimpleSearchView searchView;

    @BindView(R.id.rvTopics)
    RecyclerView rvTopics;

    private TopicAdapter adapter;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_topic);
        ButterKnife.bind(this);

        setSupportActionBar(toolbar);
        ActionBar actionBar = getSupportActionBar();
        if (actionBar != null) {
            actionBar.setTitle(ActivityTitles.getInstance(getApplicationContext()).getSubsectionName());
            actionBar.setDisplayHomeAsUpEnabled(true);
        }

        adapter = new TopicAdapter(getApplicationContext(), getController());
        rvTopics.setAdapter(adapter);
        rvTopics.setLayoutManager(new LinearLayoutManager(this));

        getWindow().setSoftInputMode(WindowManager.LayoutParams.SOFT_INPUT_STATE_HIDDEN);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        if (item.getItemId() == android.R.id.home)
            this.onBackPressed();

        return super.onOptionsItemSelected(item);
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
    protected void observe(TopicViewModel viewModel) {
        super.observe(viewModel);

        viewModel.topics().observe(this, new Observer<List<TopicModel>>() {
            @Override
            public void onChanged(@Nullable List<TopicModel> topicModels) {
                adapter.setData(topicModels);
                rvTopics.setVisibility(View.VISIBLE);
            }
        });

        viewModel.selectedTopic().observe(this, new Observer<Integer>() {
            @Override
            public void onChanged(@Nullable Integer integer) {
                if (integer == -123)
                    return;
                ActivityTitles.getInstance(getApplicationContext())
                        .setTopicName(getViewModel().topics().getValue().get(getViewModel().selectedTopic().getValue()).getTopicName());

                ActivityNavigation.getInstance(getApplicationContext())
                        .setTopicId(viewModel.topics().getValue().get(integer).getTopicId());

                CustomDialog customDialog = new CustomDialog();
                customDialog.setHeadline(getString(R.string.topic_selected) +
                        " \"" +
                        getViewModel().topics().getValue().get(integer).getTopicName() +
                        "\". " +
                        getString(R.string.ready_to_start));
//                customDialog.setListener(TopicActivity.this);
                customDialog.show(getSupportFragmentManager(), CustomDialog.class.getSimpleName());
            }
        });
    }

    @Override
    protected void observeClicks(TopicController controller) {
        super.observeClicks(controller);


    }

    @Override
    protected TopicController createController(TopicViewModel viewModel) {
        TopicController topicController = new TopicController(viewModel);
        topicController.setContext(getApplicationContext());
        return topicController;
    }

    @Override
    protected Class<TopicViewModel> viewModel() {
        return TopicViewModel.class;
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

        ActivityUtility.invokeNewActivity(TopicActivity.this, com.morozov.quiz.controller.app.subsection.SubsectionActivity.class, true);
    }

    @Override
    public void onCancelClicked() {

    }

    @Override
    public void onOkClicked() {
        ActivityUtility.invokeNewActivity(TopicActivity.this, QuizActivity.class, true);
    }
}
