package com.keep.live.service;

import android.app.Service;
import android.content.ComponentName;
import android.content.Intent;
import android.content.ServiceConnection;
import android.os.IBinder;

/**
 * 雙進程守護 本地服務
 */
public class LocalService extends ForegroundService {
    @Override
    public int onStartCommand(Intent intent, int flags, int startId) {
        bindService(new Intent(LocalService.this, RemoteService.class), mServiceConnection, Service.BIND_IMPORTANT);
        return super.onStartCommand(intent, flags, startId);
    }

    private ServiceConnection mServiceConnection = new ServiceConnection() {
        @Override
        public void onServiceConnected(ComponentName name, IBinder service) {

        }

        @Override
        public void onServiceDisconnected(ComponentName name) {
            startService(new Intent(LocalService.this, RemoteService.class));
            bindService(new Intent(LocalService.this, RemoteService.class), mServiceConnection, Service.BIND_IMPORTANT);
        }
    };
}
