package com.qinyaoz.baselib.protocol.dispatcher;

import android.content.Context;
import android.content.res.Configuration;
import android.support.annotation.IntDef;
import android.support.annotation.Size;

import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;

/**
 * 为各组件构建application的生命周期回调
 */
public interface ComponentLifecycleCallbacks {
    int TYPE_BASE_LIB = 0;
    int TYPE_FUNCTIONAL = 1;
    int TYPE_BUSINESS = 2;
    int TYPE_APP = 3;

    /**
     * 定义组件类型
     */
    @Retention(RetentionPolicy.SOURCE)
    @IntDef({TYPE_BASE_LIB, TYPE_FUNCTIONAL, TYPE_BUSINESS, TYPE_APP})
    @interface Type{}

    @Type int getType();

    /**
     * 定义统一类型组件初始化优先级别
     * 数值越小 优先级越高
     */
    @Retention(RetentionPolicy.SOURCE)
    @Size(min = 0, max = 1000)
    @interface Priority{}

    @Priority int getPriority();

    void attachBaseContext(Context context);
    void onCreate();
    void onTerminate();
    void onConfigurationChanged(Configuration newConfig);
    void onLowMemory();
    void onTrimMemory(int level);
}
