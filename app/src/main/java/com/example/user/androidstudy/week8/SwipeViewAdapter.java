package com.example.user.androidstudy.week8;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.user.androidstudy.R;
import com.h6ah4i.android.widget.advrecyclerview.swipeable.SwipeableItemAdapter;
import com.h6ah4i.android.widget.advrecyclerview.swipeable.SwipeableItemConstants;
import com.h6ah4i.android.widget.advrecyclerview.swipeable.action.SwipeResultAction;
import com.h6ah4i.android.widget.advrecyclerview.swipeable.action.SwipeResultActionDefault;
import com.h6ah4i.android.widget.advrecyclerview.swipeable.action.SwipeResultActionRemoveItem;
import com.h6ah4i.android.widget.advrecyclerview.swipeable.annotation.SwipeableItemDrawableTypes;
import com.h6ah4i.android.widget.advrecyclerview.swipeable.annotation.SwipeableItemResults;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by USER on 2017-11-09.
 */
public class SwipeViewAdapter extends RecyclerView.Adapter<SwipeViewHolder> implements SwipeableItemAdapter<SwipeViewHolder> {

    interface Swipeable extends SwipeableItemConstants {
    }

    List<Item> itemList;

    public SwipeViewAdapter() {
        setHasStableIds(true); // this is required for swiping feature.

        itemList = new ArrayList<>();
        for (int i = 0; i < 20; i++) {
            itemList.add(new Item(i, "Item " + i));
        }
    }

    @Override
    public long getItemId(int position) {
        return itemList.get(position).id; // need to return stable (= not change even after position changed) value
    }

    @Override
    public SwipeViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(parent.getContext()).inflate(R.layout.swipe_item_view, parent, false);
        return new SwipeViewHolder(v);
    }

    @Override
    public void onBindViewHolder(SwipeViewHolder holder, int position) {
        Item item = itemList.get(position);
        holder.textView.setText(item.text);
    }

    @Override
    public int getItemCount() {
        return itemList.size();
    }

    @Override
    public void onSwipeItemStarted(SwipeViewHolder holder, int position) {
        notifyDataSetChanged();
    }

    @Override
    public SwipeResultAction onSwipeItem(SwipeViewHolder holder, int position, @SwipeableItemResults int result) {
        if (result == Swipeable.RESULT_CANCELED) {
            return new SwipeResultActionDefault();
        } else {
            return new MySwipeResultActionRemoveItem(this, position);
        }
    }

    @Override
    public int onGetSwipeReactionType(SwipeViewHolder holder, int position, int x, int y) {
        return Swipeable.REACTION_CAN_SWIPE_BOTH_H;
    }

    @Override
    public void onSetSwipeBackground(SwipeViewHolder holder, int position, @SwipeableItemDrawableTypes int type) {
    }

    static class MySwipeResultActionRemoveItem extends SwipeResultActionRemoveItem {
        private SwipeViewAdapter adapter;
        private int position;

        public MySwipeResultActionRemoveItem(SwipeViewAdapter adapter, int position) {
            this.adapter = adapter;
            this.position = position;
        }

        @Override
        protected void onPerformAction() {
            adapter.itemList.remove(position);
            adapter.notifyItemRemoved(position);
        }
    }
}
