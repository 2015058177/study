package com.example.user.androidstudy.week10;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.ListView;

import com.example.user.androidstudy.R;

import java.util.ArrayList;

/**
 * Created by USER on 2017-11-22.
 */

public class ListViewActvity extends Activity implements OnClickListener {

    private ListView lv_android;
    private AndroidListAdapter list_adapter;
    private Button btn_calendar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_list_view);

        CalendarCollection.date_collection_arr=new ArrayList<CalendarCollection>();
        CalendarCollection.date_collection_arr.add(new CalendarCollection("2017-12-01","John Birthday"));
        CalendarCollection.date_collection_arr.add(new CalendarCollection("2017-09-04","Client Meeting at 5 p.m."));
        CalendarCollection.date_collection_arr.add(new CalendarCollection("2017-11-06","A Small Party at my office"));
        CalendarCollection.date_collection_arr.add(new CalendarCollection("2017-10-02","Marriage Anniversary"));
        CalendarCollection.date_collection_arr.add(new CalendarCollection("2017-11-11","Live Event and Concert of sonu"));

        getWidget();
    }

    public void getWidget(){
        btn_calendar = (Button) findViewById(R.id.btn_calendar);
        btn_calendar.setOnClickListener(this);

        lv_android = (ListView) findViewById(R.id.lv_android);
        list_adapter=new AndroidListAdapter(ListViewActvity.this,R.layout.list_item, CalendarCollection.date_collection_arr);
        lv_android.setAdapter(list_adapter);

    }

    @Override
    public void onClick(View v) {
        // TODO Auto-generated method stub
        switch (v.getId()) {
            case R.id.btn_calendar:
                startActivity(new Intent(ListViewActvity.this,CalendarActvity.class));

                break;

            default:
                break;
        }

    }

}
