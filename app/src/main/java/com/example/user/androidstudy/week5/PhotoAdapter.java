package com.example.user.androidstudy.week5;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.example.user.androidstudy.R;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by USER on 2017-07-23.
 */

public class PhotoAdapter extends RecyclerView.Adapter<PhotoAdapter.PhotoViewHolder>{

    private static final String TAG = PhotoAdapter.class.getSimpleName();

    private Context mContext;
    private LayoutInflater mInflater;

    private List<Photo> photoList = new ArrayList<>();
    private PhotoClickListener mClickListener;

    public PhotoAdapter(Context context){
        mContext = context;
        mInflater = LayoutInflater.from(context);
    }

    public void setOnItemClickListener(PhotoClickListener listener){
        mClickListener=listener;
    }

    @Override
    public PhotoViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        Log.d(TAG, "onCreateViewHolder is called");
        return new PhotoViewHolder(mInflater.inflate(R.layout.item_photo, parent, false));
    }

    @Override
    public void onBindViewHolder(PhotoViewHolder holder, int position) {
        Log.d(TAG, "onBindViewHolder is called");
        Photo photo = photoList.get(position);
        holder.bind(photo);
    }

    @Override
    public int getItemCount() {
        Log.d(TAG, "getitmecount is called with count:"+ photoList.size());
        return photoList.size();
    }

    public void addPhotoList(List<Photo>photoList){
        this.photoList.addAll(photoList);
    }

    class PhotoViewHolder extends RecyclerView.ViewHolder{

        private ImageView photoImage;
        private TextView photoPath;

        private Photo photo;

        public PhotoViewHolder(View itemView) {
            super(itemView);
            photoImage = (ImageView) itemView.findViewById(R.id.photo_image);
            photoPath=(TextView) itemView.findViewById(R.id.photo_path);

            itemView.setOnClickListener(new OnClickListener(){
                @Override
                public void onClick(View v){
                    mClickListener.onClickPhoto(photo);
                }
            });
        }

        public void bind(Photo photo) {
            this.photo = photo;

            Glide.with(mContext).load(photo.getPath()).into(photoImage);
            photoPath.setText(photo.getPath());
        }}

        public interface  PhotoClickListener{
            public void onClickPhoto(Photo photo);

            void OnClickPhoto(Photo photo);
        }


}
