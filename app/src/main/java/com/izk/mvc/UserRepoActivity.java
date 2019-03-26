package com.izk.mvc;

import android.content.Context;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.View;
import android.view.inputmethod.InputMethodManager;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.ProgressBar;
import android.widget.TextView;


import com.izk.mvc.adapter.RepositoryAdapter;
import com.izk.mvc.model.GithubService;
import com.izk.mvc.model.Repository;

import java.util.List;

import rx.Observer;
import rx.android.schedulers.AndroidSchedulers;
import rx.schedulers.Schedulers;

public class UserRepoActivity extends AppCompatActivity implements View.OnClickListener{

    private Toolbar toolbar ;
    private EditText edit_text_username ;
    private ProgressBar progress ;
    private TextView text_info ;
    private RecyclerView recyclerView;
    private RepositoryAdapter adapter ;
    private ImageButton button_search ;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_user_repo);

        toolbar = (Toolbar) findViewById(R.id.toolbar);
        edit_text_username = (EditText) findViewById(R.id.edit_text_username);
        progress = (ProgressBar) findViewById(R.id.progress);
        text_info = (TextView) findViewById(R.id.text_info);
        recyclerView = (RecyclerView) findViewById(R.id.repos_recycler_view);
        button_search = (ImageButton) findViewById(R.id.button_search);

        setSupportActionBar(toolbar);
        addTextListener();
        button_search.setOnClickListener(this);

        String username = getIntent().getStringExtra("username");
        edit_text_username.setText(username);
        loadGithubRepos(username) ;



    }


    private void addTextListener(){
        edit_text_username.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }
            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                button_search.setVisibility(s.length() > 0 ? View.VISIBLE : View.GONE);
            }
            @Override
            public void afterTextChanged(Editable s) {
            }
        });
    }


    private void loadGithubRepos(String username) {
        GithubService.Factory.create().publicRepositories(username)
                .observeOn(AndroidSchedulers.mainThread())
                .subscribeOn(Schedulers.io())
                .subscribe(new Observer<List<Repository>>() {

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
                    public void onNext(List<Repository> repositories) {
                        progress.setVisibility(View.GONE);
                        if(repositories != null){
                            recyclerView.setVisibility(View.VISIBLE);
                            setupRecyclerView(recyclerView,repositories);
                        }
                    }
                });

    }

    private void setupRecyclerView(RecyclerView recyclerView,List<Repository> repos) {
        adapter = new RepositoryAdapter(this,repos) ;
        recyclerView.setAdapter(adapter);
        recyclerView.setLayoutManager(new LinearLayoutManager((this)));
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.button_search:
                hideSoftKeyboard();
                text_info.setVisibility(View.GONE);
                progress.setVisibility(View.VISIBLE);
                recyclerView.setVisibility(View.GONE);
                loadGithubRepos(edit_text_username.getText().toString()) ;
                break ;
        }
    }

    private void hideSoftKeyboard() {
        InputMethodManager imm = (InputMethodManager) getSystemService(Context.INPUT_METHOD_SERVICE);
        imm.hideSoftInputFromWindow(edit_text_username.getWindowToken(),0) ;
    }
}