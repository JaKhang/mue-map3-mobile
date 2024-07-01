package com.mue.music.util;

import android.app.ActivityManager;
import android.content.Context;
import android.os.Build;

public class ServiceUtils {
    public static boolean isServiceRunning(Context context, Class<?> serviceClass) {
        ActivityManager activityManager = (ActivityManager) context.getSystemService(Context.ACTIVITY_SERVICE);

        if (activityManager != null) {
            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
                for (ActivityManager.RunningServiceInfo service : activityManager.getRunningServices(Integer.MAX_VALUE)) {
                    if (serviceClass.getName().equals(service.service.getClassName())) {
                        return true;
                    }
                }
            } else {
                for (ActivityManager.RunningAppProcessInfo processInfo : activityManager.getRunningAppProcesses()) {
                    if (processInfo.processName.equals(serviceClass.getName())) {
                        return true;
                    }
                }
            }
        }
        return false;
    }
}
