package com.yanfeng.ti2.callback;

import com.yanfeng.ti2.bean.Bean;

public interface CallBack {
    void onSuccess(Bean bean);
    void onFail(String s);
}
