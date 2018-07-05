package com.haier.jiuzhidaophone;

import android.content.Context;
import android.content.res.Configuration;
import android.os.Environment;
import android.support.multidex.MultiDex;
import android.support.multidex.MultiDexApplication;

import com.example.shining.libglin.glin.interceptor.IResultInterceptor;
import com.example.shining.libglin.juhenet.JuheNet;
import com.example.shining.libglin.net.Net;
import com.haier.cellarette.baselibrary.changelanguage.LocalManageUtil;
import com.haier.cellarette.libglide37.glide.GlideOptionsFactory;
import com.haier.cellarette.libretrofit.RetrofitNetNew;
import com.haier.cellarette.libutils.utilslib.app.MyLogUtil;
import com.haier.cellarette.libwebview.hois2.HiosHelper;
import com.haier.jiuzhidaophone.interceptor.Appdemo1ResultInterceptor;
import com.haier.uhome.usdk.api.uSDKManager;

public class AppApplication extends MultiDexApplication {
    public static final String DIR_PROJECT = "/jiuzhidaophone/app/";
    public static final String DIR_CACHE = DIR_PROJECT + "cache/"; // 网页缓存路径
    public static final String IMG_CACHE = DIR_PROJECT + "image/"; // image缓存路径
    public static final String VIDEO_CACHE = DIR_PROJECT + "video/"; // video缓存路径
    public static final String MUSIC_CACHE = DIR_PROJECT + "music/"; // music缓存路径

    protected void attachBaseContext(Context base) {
        //保存系统选择语言
        LocalManageUtil.saveSystemCurrentLanguage(base);
        super.attachBaseContext(base);
        MultiDex.install(this);
    }

    @Override
    public void onConfigurationChanged(Configuration newConfig) {
        super.onConfigurationChanged(newConfig);
        //保存系统选择语言
        LocalManageUtil.onConfigurationChanged(getApplicationContext());
    }

    @Override
    public void onCreate() {
        super.onCreate();
        LocalManageUtil.setApplicationLanguage(this);
        MyLogUtil.on(true);
        GlideOptionsFactory.init(this, R.drawable.ic_def_loading);
        configHios();
        configNet(true, new Appdemo1ResultInterceptor());
        configJuheNet(true, new Appdemo1ResultInterceptor());
        configRetrofitNet();
        //初始化统计

        //初始化U+sdk
        initUSDK();
    }


    /**
     * 初始化U+sdk
     */
    private void initUSDK() {
        uSDKManager sdkManager = uSDKManager.getSingleInstance();
        sdkManager.init(this);
    }


    private void configHios() {
//        HiosRegister.load();
        HiosHelper.config("ad.web.page", "web.page");
        // 接收部分
        // private int mAction; // default 0
        // private String mSkuId; // maybe null
        // private String mCategoryId;
        // mAction = getIntent().getIntExtra("act", 0);
        // mSkuId = getIntent().getStringExtra("sku_id");
        // mCategoryId = getIntent().getStringExtra("category_id");
    }

    protected void configNet(boolean debug, IResultInterceptor interceptor) {
        String cacheDir = Environment.getExternalStorageDirectory() + DIR_CACHE;

        new Net.Builder()
                .baseUrl("")
                .debug(debug)
                .timeout(30 * 1000)
                .cacheDir(cacheDir)
                .cacheSize(1024 * 1024)
                .resultInterceptor(interceptor)
                .build();
    }

    protected void configJuheNet(boolean debug, IResultInterceptor interceptor) {
        String cacheDir = Environment.getExternalStorageDirectory() + DIR_CACHE;

        new JuheNet.Builder()
                .baseUrl("")
                .debug(debug)
                .timeout(30 * 1000)
                .cacheDir(cacheDir)
                .cacheSize(1024 * 1024)
                .resultInterceptor(interceptor)
                .build();
    }

    protected void configRetrofitNet() {
        String cacheDir = Environment.getExternalStorageDirectory() + DIR_CACHE;
        // https://api-cn.faceplusplus.com/
//        RetrofitNet.config();
        RetrofitNetNew.config();
    }
}
