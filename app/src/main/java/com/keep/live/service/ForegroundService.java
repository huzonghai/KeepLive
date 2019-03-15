package com.keep.live.service;

import android.app.Notification;
import android.app.NotificationChannel;
import android.app.NotificationManager;
import android.app.Service;
import android.content.Intent;
import android.os.Binder;
import android.os.Build;
import android.os.IBinder;
import android.support.v4.app.NotificationCompat;

/**
 * 2 通过开启前台服务
 * （版本兼容）
 */
public class ForegroundService extends Service {
    public static final int SERVICE_ID = 1;

    /**
     * @param intent
     * @return  用于进程
     */
    @Override
    public IBinder onBind(Intent intent) {
        return new LocalBinder();
    }

    private class LocalBinder extends Binder {

    }


    @Override
    public int onStartCommand(Intent intent, int flags, int startId) {
        //开启前台服务
        if (Build.VERSION.SDK_INT < 18) {
            startForeground(SERVICE_ID, new Notification());
        } else if (Build.VERSION.SDK_INT < 26) {
            // 会在通知栏显示  前台service通知根据id来识别，所以关掉相同id的service来达到消除通知显示
            startForeground(SERVICE_ID, new Notification());
            startService(new Intent(this, InnerService.class));
        } else {
            //设置channel
            NotificationManager manager = (NotificationManager) getSystemService(NOTIFICATION_SERVICE);
            NotificationChannel channel = new NotificationChannel("channel", "zz", NotificationManager.IMPORTANCE_MIN);//值越小通知性月底
            if (null != channel) {
                manager.createNotificationChannel(channel);
                Notification notification = new NotificationCompat.Builder(this, "channel").build();
                startForeground(SERVICE_ID,notification);
            }
        }

        return super.onStartCommand(intent, flags, startId);
    }


    public static class InnerService extends Service {
        @Override
        public IBinder onBind(Intent intent) {
            return null;
        }

        @Override
        public int onStartCommand(Intent intent, int flags, int startId) {
            startForeground(SERVICE_ID, new Notification());
            stopForeground(true);
            stopSelf();
            return super.onStartCommand(intent, flags, startId);
        }
    }
}
