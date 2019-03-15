

#KeepLive

# Android 8.0以下 几点保活方案


    /** 1  一像素*/
          KeepManger.getInstance().registerKeep(this);

          /**2 前台服务*/
          startService(new Intent(this, ForegroundService.class));

          /**3 双进程守护*/
          startService(new Intent(this, LocalService.class));
          startService(new Intent(this, RemoteService.class));





