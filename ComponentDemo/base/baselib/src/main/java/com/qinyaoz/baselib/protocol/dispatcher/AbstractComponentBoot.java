package com.qinyaoz.baselib.protocol.dispatcher;

import android.app.Application;
import android.content.res.Configuration;
import android.util.Log;
public abstract class AbstractComponentBoot implements ComponentLifecycleCallbacks{
    protected final static String TAG = "ComponentBoot";

    /**
     * 设置组件名称
     */
    public abstract String getName();

    @Override
    public void attachBaseContext(Application context) {
        Log.i(TAG, getName() + ": attachBaseContext called");
    }

    @Override
    public void onCreate() {
        Log.i(TAG, getName() + ": onCreate called");
    }

    @Override
    public void onTerminate() {
        Log.i(TAG, getName() + ": onTerminate called");
    }

    @Override
    public void onConfigurationChanged(Configuration newConfig) {
        Log.i(TAG, getName() + ": onConfigurationChanged called");
    }

    @Override
    public void onLowMemory() {
        Log.i(TAG, getName() + ": onLowMemory called");
    }

    @Override
    public void onTrimMemory(int level) {
        Log.i(TAG, getName() + ": onTrimMemory called, level = " + level);
    }
}
