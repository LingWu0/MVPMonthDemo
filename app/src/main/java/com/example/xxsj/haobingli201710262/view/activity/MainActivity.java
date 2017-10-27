package com.example.xxsj.haobingli201710262.view.activity;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.Toast;

import com.example.xxsj.haobingli201710262.R;
import com.example.xxsj.haobingli201710262.model.bean.DataBean;
import com.example.xxsj.haobingli201710262.presenter.RegisterPersenter;
import com.example.xxsj.haobingli201710262.view.IView.IRegisterView;
import com.example.xxsj.haobingli201710262.view.adapter.RecyclerViewAdapter;
import com.google.gson.Gson;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

/**
 * 类的用途：使用MVP实现数据的展示以及购物车
 * 作者：郝兵丽
 * 日期:2017/10/26
 */

public class MainActivity extends AppCompatActivity implements IRegisterView {
    private RegisterPersenter registerPersenter;
    private RecyclerView recyclerView;
    private List<DataBean.SongListBean> list = new ArrayList<>();
    private RecyclerViewAdapter adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        recyclerView = (RecyclerView) findViewById(R.id.recyclerView);
        registerPersenter = new RegisterPersenter(this);

        registerPersenter.Register();
    }

   /* public void Register(View view) {

    }*/

    @Override
    public void onRegisterSucced(String dataBean) {
        Gson gson = new Gson();
        DataBean bean = gson.fromJson(dataBean, DataBean.class);
        list.addAll(bean.getSong_list());
        runOnUiThread(new Runnable() {
            private LinearLayoutManager linearLayoutManager;

            @Override
            public void run() {
                linearLayoutManager = new LinearLayoutManager(MainActivity.this);
                linearLayoutManager.setOrientation(LinearLayoutManager.VERTICAL);
                recyclerView.setLayoutManager(linearLayoutManager);
                adapter = new RecyclerViewAdapter(MainActivity.this, list);
                recyclerView.setAdapter(adapter);
                adapter.setOnItemClickListener(new RecyclerViewAdapter.OnItemClickListener() {
                    @Override
                    public void onItemClick(View view, String image, String geming, int price) {
                        Intent intent = new Intent(MainActivity.this, ShoppingActivity.class);
                        intent.putExtra("image", image);
                        intent.putExtra("geming", geming);
                        intent.putExtra("price", price);
                        startActivity(intent);
                    }
                });
            }
        });
    }

    @Override
    public void onRegisterFail(IOException exception) {

        runOnUiThread(new Runnable() {
            @Override
            public void run() {
                Toast.makeText(MainActivity.this, "请求失败", Toast.LENGTH_SHORT).show();
            }
        });
    }
}
