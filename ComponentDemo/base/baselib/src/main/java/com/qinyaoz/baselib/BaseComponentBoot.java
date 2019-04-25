package com.qinyaoz.baselib;

import com.alibaba.android.arouter.launcher.ARouter;
import com.google.auto.service.AutoService;
import com.qinyaoz.baselib.protocol.dispatcher.AbstractComponentBoot;
import com.qinyaoz.baselib.protocol.dispatcher.ComponentBootDispatcher;

@AutoService(AbstractComponentBoot.class)
public class BaseComponentBoot extends AbstractComponentBoot {

    @Override
    public String getName() {
        return "baseLib";
    }

    @Override
    public int getType() {
        return TYPE_BASE_LIB;
    }

    @Override
    public int getPriority() {
        return 0;
    }

    @Override
    public void onCreate() {
        super.onCreate();
        initARouter();
    }

    /**
     * 初始化ARouter相关
     */
    private void initARouter() {
        if (BuildConfig.DEBUG) {
            ARouter.openLog();
            ARouter.openDebug();
        }
        ARouter.init(ComponentBootDispatcher.getInstance().getApplication());
    }

    @Override
    public void onTerminate() {
        super.onTerminate();
        ARouter.getInstance().destroy();
    }
}
