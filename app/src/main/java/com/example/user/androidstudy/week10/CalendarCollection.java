package com.example.user.androidstudy.week10;

import java.util.ArrayList;

/**
 * Created by USER on 2017-11-22.
 */

public class CalendarCollection {
    public String date="";
    public String event_message="";

    public static ArrayList<CalendarCollection> date_collection_arr;
    public CalendarCollection(String date,String event_message){

        this.date=date;
        this.event_message=event_message;

    }
}
