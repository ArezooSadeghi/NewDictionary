package com.example.newdictionary.controller.activity;

import android.app.SearchManager;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.newdictionary.R;
import com.example.newdictionary.adapter.WordAdapter;
import com.example.newdictionary.model.Word;
import com.example.newdictionary.repository.IRepository;
import com.example.newdictionary.repository.WordDBRepository;

import java.util.List;

public class SearchableActivity extends AppCompatActivity {
    private IRepository mRepository;
    private RecyclerView mRecyclerView;
    private WordAdapter mAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_searchable);

        findViews();
        initViews();
        receiveQuery();

    }


    private void findViews() {
        mRecyclerView = findViewById(R.id.recycler_view);
    }

    private void initViews() {
        mRecyclerView.setLayoutManager(new LinearLayoutManager(this));
    }

    private void receiveQuery() {
        Intent intent = getIntent();
        if (Intent.ACTION_SEARCH.equals(intent.getAction())) {
            String query = intent.getStringExtra(SearchManager.QUERY);
            List<Word> searchResult = doMySearch(query);
            if (mAdapter == null) {
                mAdapter = new WordAdapter(searchResult, this);
                setAdapter();
            }
        }
    }

    private List<Word> doMySearch(String query) {
        mRepository = WordDBRepository.getInstance(getApplicationContext());
        List<Word> words = mRepository.searchResult(query);
        return words;
    }

    private void setAdapter() {
        mRecyclerView.setAdapter(mAdapter);
    }
}