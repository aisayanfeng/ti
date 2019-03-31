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

public class MyAdapter1 extends RecyclerView.Adapter<MyAdapter1.ViewHolder> {
    private ArrayList<Bean.ResultsBean> list;
    private Context context;

    public MyAdapter1(ArrayList<Bean.ResultsBean> list, Context context) {
        this.list = list;
        this.context = context;
    }

    public void setList(ArrayList<Bean.ResultsBean> list) {
        this.list = list;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        View inflate = LayoutInflater.from(context).inflate(R.layout.item, null);
        ViewHolder viewHolder = new ViewHolder(inflate);
        return viewHolder;
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder viewHolder, final int i) {
        Bean.ResultsBean resultsBean = list.get(i);
        viewHolder.name1.setText(resultsBean.get_id());
        viewHolder.title1.setText(resultsBean.getDesc());
        Glide.with(context).load(resultsBean.getUrl()).apply(new RequestOptions().circleCrop()).into(viewHolder.i);

        viewHolder.name2.setText(resultsBean.get_id());
        viewHolder.title2.setText(resultsBean.getDesc());
        Glide.with(context).load(resultsBean.getUrl()).apply(new RequestOptions().circleCrop()).into(viewHolder.ii);

        viewHolder.name3.setText(resultsBean.get_id());
        viewHolder.title3.setText(resultsBean.getDesc());
        Glide.with(context).load(resultsBean.getUrl()).apply(new RequestOptions().circleCrop()).into(viewHolder.iii);
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

        private final ImageView i;
        private final TextView name1;
        private final TextView title1;
        private final ImageView ii;
        private final TextView name2;
        private final TextView title2;
        private final ImageView iii;
        private final TextView name3;
        private final TextView title3;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            i = itemView.findViewById(R.id.i);
            name1 = itemView.findViewById(R.id.tv_name1);
            title1 = itemView.findViewById(R.id.tv_title1);
            ii = itemView.findViewById(R.id.ii);
            name2 = itemView.findViewById(R.id.tv_name2);
            title2 = itemView.findViewById(R.id.tv_title2);
            iii= itemView.findViewById(R.id.iii);
            name3 = itemView.findViewById(R.id.tv_name3);
            title3 = itemView.findViewById(R.id.tv_title3);
        }
    }
    private onItem onItem;

    public void setOnItem(MyAdapter1.onItem onItem) {
        this.onItem = onItem;
    }

    public interface onItem{
        void onItemClick(int i);
    }
}
