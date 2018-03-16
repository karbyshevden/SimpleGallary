package com.karbyshev.simplegallary.adapter;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.karbyshev.simplegallary.view.IMainView;
import com.karbyshev.simplegallary.R;

import java.util.ArrayList;
import java.util.List;

public class MyAdapter extends RecyclerView.Adapter<MyAdapter.MyViewHolder> {
    private Context context;
    private ArrayList<MyItem> myItemArrayList = new ArrayList<>();
    private IMainView mListener;


    public void setOnItemClickListener(IMainView mListener) {
        this.mListener = mListener;
    }

    public MyAdapter(Context context) {
        this.context = context;
    }

    @NonNull
    @Override
    public MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.item, parent, false);
        return new MyViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull MyViewHolder holder, int position) {
        MyItem cuurentItem = myItemArrayList.get(position);
        String creatorName = cuurentItem.getmCreatorName();
        String imageUrl = cuurentItem.getmImageUrl();

        holder.mTextView.setText(creatorName);
        Glide.with(context).load(imageUrl).into(holder.mImageView);

    }

    @Override
    public int getItemCount() {
        return myItemArrayList.size();
    }

    public class MyViewHolder extends RecyclerView.ViewHolder{
        private TextView mTextView;
        private ImageView mImageView;

        public MyViewHolder(View itemView) {
            super(itemView);
            mTextView = (TextView)itemView.findViewById(R.id.my_item_textView);
            mImageView = (ImageView)itemView.findViewById(R.id.my_item_imageView);

            itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    if (mListener != null){
                        int position = getAdapterPosition();
                        if (position != RecyclerView.NO_POSITION);
                        mListener.onItemClick(position);
                    }
                }
            });
        }
    }

    public void setData(List<MyItem> newData){
        myItemArrayList.clear();
        myItemArrayList.addAll(newData);
        notifyDataSetChanged();
    }
}
