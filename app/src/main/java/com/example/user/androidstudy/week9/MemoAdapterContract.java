package com.example.user.androidstudy.week9;

import io.realm.RealmResults;

/**
 * Created by USER on 2017-11-16.
 */

public interface MemoAdapterContract{

    interface View{
        void notifyMemoRangeInserted(int from, int count);
        void notifyMemoRangeDeleted(int from, int count);
        void notifyMemoRangeModified(int from, int count);

        void notifyMemoSetChanged();

        void setOnMemoClickListener(MemoAdapter.OnMemoClickListener listener);


    }
    interface Model{
        void init(RealmResults<Memo> memoRealmResults);
    }
}
