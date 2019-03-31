package com.yanfeng.ti2;

import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.net.Uri;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.bumptech.glide.Glide;
import com.yanfeng.ti2.adapter.MyAdapter;
import com.yanfeng.ti2.bean.Bean;
import com.yanfeng.ti2.model.MainModelImpl;
import com.yanfeng.ti2.presenter.MainPresenterImpl;
import com.yanfeng.ti2.view.MainView;

import java.util.ArrayList;

public class Main2Activity extends AppCompatActivity implements MainView, View.OnClickListener {

    private ImageView i;
    private TextView name;
    private TextView title;
    private RecyclerView xr;
    private ArrayList<Bean.ResultsBean> list = new ArrayList<>();
    private MyAdapter adapter;
    private TextView tv;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main2);
        initView();
        initData();
        initIntent();
    }

    private void initIntent() {
        String name1 = getIntent().getStringExtra("name");
        String title1 = getIntent().getStringExtra("title");
        String url = getIntent().getStringExtra("url");
        name.setText(name1);
        title.setText(title1);
        Glide.with(this).load(url).into(i);
    }

    private void initData() {
        MainPresenterImpl mainPresenter = new MainPresenterImpl(new MainModelImpl(), this);
        mainPresenter.ongetData();
    }

    private void initView() {
        i = (ImageView) findViewById(R.id.i);
        name = (TextView) findViewById(R.id.name);
        title = (TextView) findViewById(R.id.title);
        xr = (RecyclerView) findViewById(R.id.xr);
        adapter = new MyAdapter(list, this);
        xr.setAdapter(adapter);
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(this);
        xr.setLayoutManager(linearLayoutManager);
        tv = (TextView) findViewById(R.id.tv);
        tv.setOnClickListener(this);
    }

    @Override
    public void onSuccess(Bean bean) {
        list.addAll(bean.getResults());
        adapter.setList(list);
        adapter.notifyDataSetChanged();
    }

    @Override
    public void onFail(String s) {
        Toast.makeText(this, s, Toast.LENGTH_SHORT).show();
    }

    @Override
    public void onClick(View v) {
        Intent intent = new Intent(Main2Activity.this, MainActivity.class);
        startActivity(intent);
    }
}
