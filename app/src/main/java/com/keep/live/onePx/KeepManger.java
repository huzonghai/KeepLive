package com.keep.live.onePx;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;

import java.lang.ref.WeakReference;

public class KeepManger {
    private static final KeepManger mKeepManger = new KeepManger();
    private KeppReceiver mKeppReceiver;
    private WeakReference<Activity> mKeepActivty;

    public static KeepManger getInstance() {
        return mKeepManger;
    }

    /**
     * 注册开屏 锁屏监听
     */
    public void registerKeep(Context context) {
        IntentFilter intentFilter = new IntentFilter();
        intentFilter.addAction(Intent.ACTION_SCREEN_ON);
        intentFilter.addAction(Intent.ACTION_SCREEN_OFF);
        mKeppReceiver = new KeppReceiver();
        context.registerReceiver(mKeppReceiver, intentFilter);
    }

    /**
     * 注销广播监听
     */
    public void unRegisterKeep(Context context) {
        if (null != mKeppReceiver) {
            context.unregisterReceiver(mKeppReceiver);

        }
    }

    /**
     * 开启一像素Act
     */
    public void startOnePxActivty(Context context) {
        Intent intent = new Intent(context, OnePxActivity.class);
        intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
        context.startActivity(intent);
    }

    /**
     * 关闭一像素Act
     */
    public void finishOnePxActivty() {
        if (null != mKeepActivty) {
            Activity activity = mKeepActivty.get();
            if (null != activity) {
                activity.finish();
            }
            mKeepActivty = null;
        }
    }

    /**
     * 设置若引用的act，防止内存泄露
     */
    public void setKeepActivty(OnePxActivity onePxActivity) {
        mKeepActivty = new WeakReference<Activity>(onePxActivity);
    }
}
