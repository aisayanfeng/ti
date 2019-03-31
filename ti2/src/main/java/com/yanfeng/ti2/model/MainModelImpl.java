package com.yanfeng.ti2.model;

import com.yanfeng.ti2.api.MyServer;
import com.yanfeng.ti2.bean.Bean;
import com.yanfeng.ti2.callback.CallBack;

import io.reactivex.Observable;
import io.reactivex.Observer;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.Disposable;
import io.reactivex.schedulers.Schedulers;
import retrofit2.Retrofit;
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory;
import retrofit2.converter.gson.GsonConverterFactory;

public class MainModelImpl implements MainModel {
    @Override
    public void getData(final CallBack callBack) {
        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl(MyServer.url)
                .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
                .addConverterFactory(GsonConverterFactory.create())
                .build();
        MyServer myServer = retrofit.create(MyServer.class);
        Observable<Bean> observable = myServer.getdata();
        observable.subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Observer<Bean>() {
                    @Override
                    public void onSubscribe(Disposable d) {

                    }

                    @Override
                    public void onNext(Bean bean) {
                        if(bean!=null){
                            callBack.onSuccess(bean);
                        }else {
                            callBack.onFail("失败");
                        }
                    }

                    @Override
                    public void onError(Throwable e) {
                        callBack.onFail(e.getMessage());
                    }

                    @Override
                    public void onComplete() {

                    }
                });
    }
}
