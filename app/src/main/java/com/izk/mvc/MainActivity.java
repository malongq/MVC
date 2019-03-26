package com.izk.mvc;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.ProgressBar;
import android.widget.TextView;


import com.izk.mvc.adapter.RepoListAdapter;
import com.izk.mvc.model.GithubService;
import com.izk.mvc.model.Repo;

import java.util.List;

import rx.Observer;
import rx.android.schedulers.AndroidSchedulers;
import rx.schedulers.Schedulers;

public class MainActivity extends AppCompatActivity {

//    private Toolbar toolbar ;
    private RecyclerView recyclerView;
    private RepoListAdapter adapter ;
    private TextView text_description ;
    private ProgressBar progress ;
    private TextView text_info ;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

//        toolbar = (Toolbar) findViewById(R.id.toolbar);
        recyclerView = (RecyclerView) findViewById(R.id.recycler);
        text_description = (TextView) findViewById(R.id.text_description);
        progress = (ProgressBar) findViewById(R.id.progress);
        text_info = (TextView) findViewById(R.id.text_info);

//        setSupportActionBar(toolbar);
        text_description.setText("GitHub Java");
        loadGitHubRepo();
    }

    private void loadGitHubRepo() {
        String url = "http://github.laowch.com/json/java_daily" ;
        GithubService.Factory.create().javaRepositories(url)
                .observeOn(AndroidSchedulers.mainThread())
                .subscribeOn(Schedulers.io())
                .subscribe(new Observer<List<Repo>>() {
                    @Override
                    public void onCompleted() {
                        text_info.setVisibility(View.GONE);
                    }

                    @Override
                    public void onError(Throwable e) {
                        progress.setVisibility(View.GONE);
                        text_info.setVisibility(View.VISIBLE);
                        recyclerView.setVisibility(View.GONE);
                    }

                    @Override
                    public void onNext(List<Repo> repos) {
                        progress.setVisibility(View.GONE);
                        if(repos != null)
                            setupRecyclerView(recyclerView,repos);
                    }
                }) ;
    }


    private void setupRecyclerView(RecyclerView recyclerView,List<Repo> repos) {
        adapter = new RepoListAdapter(this,repos) ;
        recyclerView.setAdapter(adapter);
        recyclerView.setLayoutManager(new LinearLayoutManager((this)));
    }
}

