package com.haier.pay;

import android.app.Activity;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.text.TextUtils;

import com.haier.biz2phonedemo1.ConstantNetConfig;


public class demoact extends Activity {
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        //版本判断
        if (TextUtils.equals(ConstantNetConfig.VERSION_NAME, "ceshi")) {

        } else if (TextUtils.equals(ConstantNetConfig.VERSION_NAME, "yushengchan")) {

        } else if (TextUtils.equals(ConstantNetConfig.VERSION_NAME, "xianshang")) {

        }


    }


}
