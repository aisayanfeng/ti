package com.yanfeng.ti2.api;

import com.yanfeng.ti2.bean.Bean;

import io.reactivex.Observable;
import retrofit2.http.GET;

public interface MyServer {
    //http://gank.io/api/data/%E7%A6%8F%E5%88%A9/20/1
    public String url="http://gank.io/api/data/%E7%A6%8F%E5%88%A9/";
    @GET("20/1")
    Observable<Bean> getdata();
}
