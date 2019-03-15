package com.keep.live;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;

import com.keep.live.service.LocalService;
import com.keep.live.service.RemoteService;

/**
 * 保活
 * 1 一像素
 * 2 前台服务
 * 3 双进程守护
 */
public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        /** 1  一像素*/
//        KeepManger.getInstance().registerKeep(this);

        /**2 前台服务*/
//        startService(new Intent(this, ForegroundService.class));

        /**3 双进程守护*/
        startService(new Intent(this, LocalService.class));
        startService(new Intent(this, RemoteService.class));
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
//        KeepManger.getInstance().unRegisterKeep(this);
    }
}
