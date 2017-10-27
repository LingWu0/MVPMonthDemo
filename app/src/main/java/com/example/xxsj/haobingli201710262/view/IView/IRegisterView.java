package com.example.xxsj.haobingli201710262.view.IView;

import java.io.IOException;

/**
 * 类的用途：使用MVP实现数据的展示以及购物车
 * 作者：郝兵丽
 * 日期:2017/10/26
 */

public interface IRegisterView {

    void onRegisterSucced(String dataBean);
    void onRegisterFail(IOException exception);
}
