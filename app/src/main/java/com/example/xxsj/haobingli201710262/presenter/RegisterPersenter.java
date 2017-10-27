package com.example.xxsj.haobingli201710262.presenter;

import android.support.annotation.NonNull;

import com.example.xxsj.haobingli201710262.model.RegisterModle;
import com.example.xxsj.haobingli201710262.view.IView.IRegisterView;

import java.io.IOException;

/**
 * 类的用途：使用MVP实现数据的展示以及购物车
 * 作者：郝兵丽
 * 日期:2017/10/26
 */

public class RegisterPersenter {
    //接口，负责回调activity
    private IRegisterView iRegisterView;

    private final RegisterModle registerModle;

    public RegisterPersenter(@NonNull IRegisterView iRegisterView) {
        this.iRegisterView = iRegisterView;
        registerModle = new RegisterModle();

    }


    public void Register() {

        registerModle.Register(new RegisterModle.DataCallBack<String>() {
            @Override
            public void GetDataSucced(String s) {
                iRegisterView.onRegisterSucced(s);
            }

            @Override
            public void GetDataFailed(IOException s) {
                iRegisterView.onRegisterFail(s);
            }
        });
    }
}
