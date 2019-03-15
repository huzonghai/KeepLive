package com.keep.live.onePx;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.text.TextUtils;

public class KeppReceiver extends BroadcastReceiver {


    @Override
    public void onReceive(Context context, Intent intent) {
        String action = intent.getAction();
        /** 锁屏启动一像素act*/
        if (TextUtils.equals(action, Intent.ACTION_SCREEN_OFF)) {
            KeepManger.getInstance().startOnePxActivty(context);
            /** 开屏结束一像素act*/
        } else if (TextUtils.equals(action, Intent.ACTION_SCREEN_ON)) {
            KeepManger.getInstance().finishOnePxActivty();
        }
    }
}
