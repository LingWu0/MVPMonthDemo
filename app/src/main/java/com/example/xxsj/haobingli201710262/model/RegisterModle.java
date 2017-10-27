package com.example.xxsj.haobingli201710262.model;

import android.support.annotation.NonNull;

import com.example.xxsj.haobingli201710262.model.app.App;

import java.io.IOException;

import okhttp3.Call;
import okhttp3.Callback;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;

/**
 * 类的用途：使用MVP实现数据的展示以及购物车
 * 作者：郝兵丽
 * 日期:2017/10/26
 */

public class RegisterModle {
    public RegisterModle() {

    }

    public void Register(@NonNull final DataCallBack<String> dataCallBack) {

        OkHttpClient okHttpClient = App.okHttpClient();
        Request request = new Request.Builder()
                .url("http://tingapi.ting.baidu.com/v1/restserver/ting?method=baidu.ting.billboard.billList&type=1&size=10&offset=0")
                .build();
        okHttpClient.newCall(request).enqueue(new Callback() {
            @Override
            public void onFailure(Call call, IOException e) {
                dataCallBack.GetDataFailed(e);
            }

            @Override
            public void onResponse(Call call, Response response) throws IOException {

                if (response.isSuccessful()) {
                    String json = response.body().string();
                    dataCallBack.GetDataSucced(json);
                }
            }
        });
    }


    public interface DataCallBack<T> {
        void GetDataSucced(T t);

        void GetDataFailed(IOException s);
    }
}
