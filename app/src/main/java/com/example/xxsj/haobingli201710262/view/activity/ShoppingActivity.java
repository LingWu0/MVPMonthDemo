package com.example.xxsj.haobingli201710262.view.activity;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.CheckBox;
import android.widget.ExpandableListView;
import android.widget.TextView;

import com.example.xxsj.haobingli201710262.R;
import com.example.xxsj.haobingli201710262.view.adapter.MyAdpater;

/**
 * 类的用途：使用MVP实现数据的展示以及购物车
 * 作者：郝兵丽
 * 日期:2017/10/26
 */

public class ShoppingActivity extends AppCompatActivity {
    private ExpandableListView listview;
    private MyAdpater adpater;
    private TextView checked_shop;
    private TextView prices;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_shopping);
        /*
        * 点击item将每一个商品信息传到购物车页面
        * */

        Intent intent = getIntent();
        String image = intent.getStringExtra("image");
        String getming = intent.getStringExtra("getming");
        String price = intent.getStringExtra("price");


        listview = (ExpandableListView) findViewById(R.id.listview);
        adpater = new MyAdpater(this);
        listview.setAdapter(adpater);
        final CheckBox checkAll = (CheckBox) findViewById(R.id.checkAll);
        prices = (TextView) findViewById(R.id.price);
        checked_shop = (TextView) findViewById(R.id.checked_shop);
        checkAll.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //设置商品全部选中
                adpater.checkAllShop(checkAll.isChecked());
                //计算选中的价格和数量
                String shopPrice = adpater.getShopPrice();
                //判断商品是否全部选中
                boolean b = adpater.selectAll();

                String[] split = shopPrice.split(",");
                prices.setText(split[0]);
                checked_shop.setText(split[1]);
                checkAll.setChecked(b);
            }
        });
        adpater.getAdapterData(new MyAdpater.AdapterData() {
            @Override
            public void Data(View v, String str, boolean b) {
                String[] split = str.split(",");
                prices.setText(split[0]);
                checked_shop.setText(split[1]);
                checkAll.setChecked(b);
            }
        });

        checkAll.setChecked(adpater.selectAll());
        adpater.notifyDataSetChanged();

    }

}
