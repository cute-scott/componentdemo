package com.qinyaoz.baselib.protocol.dispatcher;

import android.content.Context;
import android.content.res.Configuration;
import android.support.multidex.MultiDexApplication;

public abstract class AbstractApplication extends MultiDexApplication {
    @Override
    protected void attachBaseContext(Context context) {
        super.attachBaseContext(context);
        ComponentBootDispatcher.getInstance().attachBaseContext(context);
    }

    @Override
    public void onCreate() {
        super.onCreate();
        ComponentBootDispatcher.getInstance().onCreate();
    }

    @Override
    public void onTerminate() {
        super.onTerminate();
        ComponentBootDispatcher.getInstance().onTerminate();
    }

    @Override
    public void onConfigurationChanged(Configuration newConfig) {
        super.onConfigurationChanged(newConfig);
        ComponentBootDispatcher.getInstance().onConfigurationChanged(newConfig);
    }

    @Override
    public void onLowMemory() {
        super.onLowMemory();
        ComponentBootDispatcher.getInstance().onLowMemory();
    }

    @Override
    public void onTrimMemory(int level) {
        super.onTrimMemory(level);
        ComponentBootDispatcher.getInstance().onTrimMemory(level);
    }
}
