package com.yanfeng.ti2.presenter;

import com.yanfeng.ti2.bean.Bean;
import com.yanfeng.ti2.callback.CallBack;
import com.yanfeng.ti2.model.MainModel;
import com.yanfeng.ti2.view.MainView;

public class MainPresenterImpl implements MainPresenter,CallBack {
    private MainModel mainModel;
    private MainView mainView;

    public MainPresenterImpl(MainModel mainModel, MainView mainView) {
        this.mainModel = mainModel;
        this.mainView = mainView;
    }

    @Override
    public void onSuccess(Bean bean) {
        if(mainView!=null){
            mainView.onSuccess(bean);
        }
    }

    @Override
    public void onFail(String s) {
        if(mainView!=null){
            mainView.onFail(s);
        }
    }

    @Override
    public void ongetData() {
        if(mainModel!=null){
            mainModel.getData(this);
        }
    }
}
