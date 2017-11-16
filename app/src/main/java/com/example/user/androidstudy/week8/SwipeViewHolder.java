package com.example.user.androidstudy.week8;

import android.view.View;
import android.widget.FrameLayout;
import android.widget.TextView;

import com.example.user.androidstudy.R;
import com.h6ah4i.android.widget.advrecyclerview.utils.AbstractSwipeableItemViewHolder;


/**
 * Created by USER on 2017-11-09.
 */

public class SwipeViewHolder extends AbstractSwipeableItemViewHolder {
    FrameLayout contaninerView;
    TextView textView;

    public SwipeViewHolder(View itemView){
        super(itemView);
        contaninerView = itemView.findViewById(R.id.swipe_view_container);
        textView = itemView.findViewById(R.id.view_text);
    }

    @Override
    public View getSwipeableContainerView(){
        return contaninerView;
    }

}
