package com.yanfeng.ti2;

import android.content.Intent;
import android.media.DrmInitData;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.widget.Toast;

import com.yanfeng.ti2.adapter.MyAdapter;
import com.yanfeng.ti2.adapter.MyAdapter1;
import com.yanfeng.ti2.bean.Bean;
import com.yanfeng.ti2.model.MainModelImpl;
import com.yanfeng.ti2.presenter.MainPresenterImpl;
import com.yanfeng.ti2.view.MainView;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity implements MainView, MyAdapter.onItem, MyAdapter1.onItem {
    private Toolbar toolbar;
    private RecyclerView xr;
    private ArrayList<Bean.ResultsBean> list=new ArrayList<>();
    private MyAdapter1 myAdapter;

    //艳风   1808D

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        initView();
        initData();
    }

    private void initData() {
        MainPresenterImpl mainPresenter = new MainPresenterImpl(new MainModelImpl(), this);
        mainPresenter.ongetData();
    }

    private void initView() {
        toolbar = (Toolbar) findViewById(R.id.toolbar);
        toolbar.setTitle("");
        setSupportActionBar(toolbar);
        myAdapter = new MyAdapter1(list, this);
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(this);
        xr = (RecyclerView) findViewById(R.id.xr);
        xr.setAdapter(myAdapter);
        xr.setLayoutManager(linearLayoutManager);
        myAdapter.setOnItem(this);
    }

    @Override
    public void onSuccess(Bean bean) {
        list.addAll(bean.getResults());
        myAdapter.setList(list);
        myAdapter.notifyDataSetChanged();
    }

    @Override
    public void onFail(String s) {
        Toast.makeText(this, s, Toast.LENGTH_SHORT).show();
    }

    @Override
    public void onItemClick(int i) {
        Bean.ResultsBean resultsBean = list.get(i);
        String desc = resultsBean.getDesc();
        String createdAt = resultsBean.getCreatedAt();
        String url = resultsBean.getUrl();
        Intent intent = new Intent(MainActivity.this, Main2Activity.class);
        intent.putExtra("name", desc);
        intent.putExtra("title", createdAt);
        intent.putExtra("url", url);
        startActivity(intent);
    }

}
