package com.example.user.androidstudy.week8;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;

import com.example.user.androidstudy.R;
import com.h6ah4i.android.widget.advrecyclerview.swipeable.RecyclerViewSwipeManager;

/**
 * Created by USER on 2017-11-09.
 */

public class SwipeViewActivity extends AppCompatActivity{

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.swipe_example);

        RecyclerView recyclerView = findViewById(R.id.swipe_recycler_view);

        RecyclerViewSwipeManager swipeManager = new RecyclerViewSwipeManager();

        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        recyclerView.setAdapter(swipeManager.createWrappedAdapter(new SwipeViewAdapter()));

        swipeManager.attachRecyclerView(recyclerView);
    }
}

