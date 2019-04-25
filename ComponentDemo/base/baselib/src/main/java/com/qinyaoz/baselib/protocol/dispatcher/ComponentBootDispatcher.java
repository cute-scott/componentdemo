package com.qinyaoz.baselib.protocol.dispatcher;

import android.app.Application;
import android.content.res.Configuration;

import java.util.Collections;
import java.util.Comparator;
import java.util.LinkedList;
import java.util.ServiceLoader;

/**
 * 组件初生命周期分发器
 */
public class ComponentBootDispatcher {
    private static ComponentBootDispatcher instance = new ComponentBootDispatcher();
    public static ComponentBootDispatcher getInstance() {
        return instance;
    }

    private Application application;

    private LinkedList<AbstractComponentBoot> container = new LinkedList<>();

    /**
     * 初始化所有组件的boot，从这里启动所有组件的初始化工作
     */
    private void init(Application context) {
        application = context;
        /*List<Class> componentClassList = null;
        try {
            componentClassList = ClassesUtil.getAllClassesByBase(AbstractComponentBoot.class);
            for (Class clz : componentClassList) {

            }
        } catch (Exception e) {
            e.printStackTrace();
        }*/
        //利用ServiceLoader加载所有ComponentBoot类，实现每个组件初始化
        ServiceLoader<AbstractComponentBoot> serviceLoader = ServiceLoader.load(AbstractComponentBoot.class);
        for (AbstractComponentBoot componentBoot : serviceLoader) {
            container.add(componentBoot);
        }
        //按照组件的优先级进行排序
        Collections.sort(container, new ComponentBootComparator());
    }

    public Application getApplication() {
        return application;
    }

    void attachBaseContext(Application context) {
        init(context);
        for (AbstractComponentBoot componentBoot : container) {
            componentBoot.attachBaseContext(context);
        }
    }

    void onCreate() {
        for (AbstractComponentBoot componentBoot : container) {
            componentBoot.onCreate();
        }
    }

    void onTerminate() {
        for (AbstractComponentBoot componentBoot : container) {
            componentBoot.onTerminate();
        }
    }

    void onConfigurationChanged(Configuration configuration) {
        for (AbstractComponentBoot componentBoot : container) {
            componentBoot.onConfigurationChanged(configuration);
        }
    }

    void onLowMemory() {
        for (AbstractComponentBoot componentBoot : container) {
            componentBoot.onLowMemory();
        }
    }

    void onTrimMemory(int level) {
        for (AbstractComponentBoot componentBoot : container) {
            componentBoot.onTrimMemory(level);
        }
    }

    private static class ComponentBootComparator implements Comparator<AbstractComponentBoot> {

        @Override
        public int compare(AbstractComponentBoot o1, AbstractComponentBoot o2) {
            int flag = o1.getType() - o2.getType();
            if (0 == flag) {
                flag = o1.getPriority() - o2.getPriority();
            }
            return flag;
        }
    }
}
