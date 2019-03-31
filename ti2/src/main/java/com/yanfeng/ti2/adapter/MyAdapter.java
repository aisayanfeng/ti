package com.yanfeng.ti2.adapter;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.request.RequestOptions;
import com.yanfeng.ti2.R;
import com.yanfeng.ti2.bean.Bean;

import java.util.ArrayList;

public class MyAdapter extends RecyclerView.Adapter<MyAdapter.ViewHolder> {
    private ArrayList<Bean.ResultsBean> list;
    private Context context;

    public MyAdapter(ArrayList<Bean.ResultsBean> list, Context context) {
        this.list = list;
        this.context = context;
    }

    public void setList(ArrayList<Bean.ResultsBean> list) {
        this.list = list;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        View inflate = LayoutInflater.from(context).inflate(R.layout.item1, null);
        ViewHolder viewHolder = new ViewHolder(inflate);
        return viewHolder;
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder viewHolder, final int i) {
        Bean.ResultsBean resultsBean = list.get(i);
        viewHolder.name.setText(resultsBean.getDesc());
        viewHolder.title.setText(resultsBean.getCreatedAt());
        Glide.with(context).load(resultsBean.getUrl()).apply(new RequestOptions().circleCrop()).into(viewHolder.img);
        viewHolder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(onItem!=null){
                    onItem.onItemClick(i);
                }
            }
        });
    }

    @Override
    public int getItemCount() {
        return list.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {

        private final ImageView img;
        private final TextView name;
        private final TextView title;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            img = itemView.findViewById(R.id.img);
            name = itemView.findViewById(R.id.tv_name);
            title = itemView.findViewById(R.id.tv_title);
        }
    }
    private onItem onItem;

    public void setOnItem(MyAdapter.onItem onItem) {
        this.onItem = onItem;
    }

    public interface onItem{
        void onItemClick(int i);
    }
}
