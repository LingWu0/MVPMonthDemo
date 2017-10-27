package com.example.xxsj.haobingli201710262.model.app;

import android.app.Application;
import android.os.Build;
import android.os.Environment;

import com.nostra13.universalimageloader.cache.disc.impl.UnlimitedDiskCache;
import com.nostra13.universalimageloader.cache.disc.naming.Md5FileNameGenerator;
import com.nostra13.universalimageloader.core.ImageLoader;
import com.nostra13.universalimageloader.core.ImageLoaderConfiguration;

import java.io.File;
import java.io.IOException;
import java.util.concurrent.TimeUnit;

import okhttp3.Interceptor;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;

/**
 * 类的用途：使用MVP实现数据的展示以及购物车
 * 作者：郝兵丽
 * 日期:2017/10/26
 */

public class App extends Application {

    private static OkHttpClient okHttpClient;

    @Override
    public void onCreate() {
        super.onCreate();
        //初始化ImageLoader
        initImageLoader();
        okHttpClient = new OkHttpClient();
        okHttpClient =okHttpClient.newBuilder()
                .connectTimeout(5000, TimeUnit.SECONDS)
                //拦截器
                .addInterceptor(new LoggingInterceptor())
                .readTimeout(5000,TimeUnit.SECONDS)
                .build();
    }

    public static OkHttpClient okHttpClient(){
        return okHttpClient;
    }

    //拦截器
    public class LoggingInterceptor implements Interceptor {
        private static final String UA = "User-Agent";

        @Override
        public Response intercept(Chain chain) throws IOException {
            Request request = chain.request()
                    .newBuilder()
                    .addHeader(UA, makeUA())
                    .build();
            return chain.proceed(request);
        }

        private String makeUA() {
            String s = Build.BRAND + "/" + Build.MODEL + "/" + Build.VERSION.RELEASE;
            return Build.BRAND + "/" + Build.MODEL + "/" + Build.VERSION.RELEASE;
        }

    }

    //初始化ImageLoader
    private void initImageLoader() {
        File file = new File(Environment.getDownloadCacheDirectory().getParent() + "/MonthImage");
        ImageLoaderConfiguration build = new ImageLoaderConfiguration.Builder(this)
                .memoryCacheSize(2 * 1024 * 1024)
                .memoryCacheExtraOptions(100, 100)
                .threadPoolSize(3)
                .threadPriority(100)
                .diskCache(new UnlimitedDiskCache(file))
                .diskCacheFileNameGenerator(new Md5FileNameGenerator())
                .diskCacheSize(100 * 1024 * 1024)
                .build();
        ImageLoader.getInstance().init(build);
    }
}
